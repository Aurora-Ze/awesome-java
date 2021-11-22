package consumer;

import comm.Config;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * 使用线程池消费不同批次的消息，并维护消费位移offset
 * @author Aurora
 * @date 2021/11/22 19:17
 */
public class MultiConsumerDemo2 {
    public static Properties initConfig() {
        Properties prop = new Properties();
        prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.BROKER_LIST);
        prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 关闭自动位移提交
        prop.setProperty("enable.auto.commit", "false");
        // 消息从头开始消费
        prop.setProperty("auto.offset.reset", "earliest");

        prop.setProperty("group.id", "group-multi-thread4");
        return prop;
    }

    static class KafkaConsumerThread extends Thread {
        KafkaConsumer consumer;
        ExecutorService executors;
        ConcurrentMap<TopicPartition, OffsetAndMetadata> offsets;

        KafkaConsumerThread(String topic, Properties prop, int threadNumber) {
            consumer = new KafkaConsumer(prop);
            consumer.subscribe(Collections.singletonList(topic));
            executors = new ThreadPoolExecutor(threadNumber, threadNumber, 0L,
                    TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000),
                    new ThreadPoolExecutor.CallerRunsPolicy());

            offsets = new ConcurrentHashMap<>();
        }

        @Override
        public void run() {
            try {
                while(true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                    if (!records.isEmpty()) {
                        executors.submit(new RecordsHandler(records, offsets));
                    }
                    // 提交位移
                    if (!offsets.isEmpty()) {
                        consumer.commitSync(offsets);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                consumer.close();
            }
        }
    }

    static class RecordsHandler implements Runnable {
        ConsumerRecords<String, String> records;
        ConcurrentMap<TopicPartition, OffsetAndMetadata> offsets;

        RecordsHandler(ConsumerRecords<String, String> records, ConcurrentMap<TopicPartition, OffsetAndMetadata> offsets) {
            this.records = records;
            this.offsets = offsets;
        }

        @Override
        public void run() {
            // 处理消息
            for(TopicPartition partition : records.partitions()) {
                List<ConsumerRecord<String, String>> partitionRecords = this.records.records(partition);

                // 消费过程...
                for(ConsumerRecord record : partitionRecords) {
                    System.out.println("Current Thread: " + Thread.currentThread().getName());
                    System.out.println("topic = " + record.topic()
                            + " partition = " + record.partition()
                            + " value = " + record.value());
                }
                // 记录位移, 条件为没有偏移量记录或消费偏移量更大 fixme
                long lastConsumedOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                if (!offsets.containsKey(partition) || lastConsumedOffset + 1 > offsets.get(partition).offset()) {
                    offsets.put(partition, new OffsetAndMetadata(lastConsumedOffset + 1));
                }
            }
        }
    }

    public static void main(String[] args) {
        KafkaConsumerThread thread = new KafkaConsumerThread(Config.TOPIC, initConfig(), 5);
        thread.run();
    }

}

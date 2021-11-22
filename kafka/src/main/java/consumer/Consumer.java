package consumer;

import comm.Config;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Aurora
 * @date 2021/11/18 14:51
 */
public class Consumer {
    private static KafkaConsumer consumer = null;
    private static AtomicBoolean isRunning = new AtomicBoolean(true);

    public static Properties initConfig() {
        Properties prop = new Properties();
        prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.BROKER_LIST);
        prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 关闭自动位移提交
        prop.setProperty("enable.auto.commit", "false");
        // 消息从头开始消费
        prop.setProperty("auto.offset.reset", "earliest");

        prop.setProperty("group.id", "group-multi-thread");
        return prop;
    }

    public static void subscribe() {
        // 订阅指定topic
        consumer.subscribe(Arrays.asList(Config.TOPIC), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                consumer.commitSync();
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                // do nothing
            }
        });

//        // 订阅指定topic和partition
//        consumer.assign(Arrays.asList(new TopicPartition(Config.TOPIC, 0)));
//
//        // 正则表达式批量订阅
//        consumer.subscribe(Pattern.compile("test*"));
//
//        // 查询并订阅topic的所有分区
//        List<TopicPartition> topic = new ArrayList<>();
//        List<PartitionInfo> partitions = consumer.partitionsFor(Config.TOPIC);
//        for (PartitionInfo partition : partitions) {
//            topic.add(new TopicPartition(partition.topic(), partition.partition()));
//        }
//        consumer.assign(topic);
    }

    public static void consume() {
        while (isRunning.get()) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(5000));
            // 针对分区进行消费
            for (TopicPartition partition : records.partitions()) {
                List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);

                for (ConsumerRecord<String, String> record : partitionRecords) {
                    System.out.println("Topic = " + record.topic()
                            + ", partition = " + record.partition()
                            + ", offset = " + record.offset());
                    System.out.println("key = " + record.key()
                            + "value = " + record.value());
                }
                // 同步提交位移
                long lastConsumedOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastConsumedOffset + 1)));
            }
        }
    }

    public static void main(String[] args) {
//        Properties prop = initConfig();
//        consumer = new KafkaConsumer<>(prop);
//        subscribe();
//        consume();



    }

}

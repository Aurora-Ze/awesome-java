package consumer;

import comm.Config;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * 开启多个线程进行消费，每个线程消费不同分区
 *
 * 消费能力受限于分区数，且有较多的连接开销
 * @author Aurora
 * @date 2021/11/22 19:10
 */
public class MultiConsumerDemo1 {
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

    public static void main(String[] args) {
        MultiConsumerDemo1 demo = new MultiConsumerDemo1();
        demo.multiConsume();
    }

    public void multiConsume() {
        Properties prop = initConfig();
        for (int i = 0; i < 4; i++) { // 线程数可指定
            ConsumerThread thread = new ConsumerThread(Config.TOPIC, prop);
            thread.start();
        }
    }

     class ConsumerThread extends Thread {
        KafkaConsumer consumer;

        ConsumerThread(String topic, Properties properties) {
            this.consumer = new KafkaConsumer(properties);
            consumer.subscribe(Collections.singletonList(topic));
        }

        @Override
        public void run() {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());
                }
            }
        }
    }
}

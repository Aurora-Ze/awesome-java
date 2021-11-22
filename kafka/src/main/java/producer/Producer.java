package producer;

import comm.Config;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Kafka生产者简单使用
 * @author Aurora
 * @date 2021/11/15 14:29
 */
public class Producer {

    public static Properties initConfig() {
        Properties prop = new Properties();
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.BROKER_LIST);
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoundRobinPartitioner.class.getName()); // 测试轮询

        // 返回ack策略
        prop.setProperty("acks", "1");
        // 消息最大值
        prop.setProperty("max.request.size", "1048576");
        // 重发次数和间隔
        prop.setProperty("retries", "10");
        prop.setProperty("retries.backoff.ms", "100");

        // 消息压缩方式
        prop.setProperty("compression.type", "none");

        // 使用自定义的拦截器
//        prop.setProperty(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, MyProducerInterceptor.class.getName());
        return prop;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties prop = initConfig();
        KafkaProducer<String, String> producer = new KafkaProducer<>(prop);


        ProducerRecord<String, String> record1 = new ProducerRecord(Config.TOPIC, "aurora");
        ProducerRecord<String, String> record2 = new ProducerRecord(Config.TOPIC, "20");
        ProducerRecord<String, String> record3 = new ProducerRecord(Config.TOPIC, "male");


        for (int i = 0; i < 10; i ++) {
            RecordMetadata recordMetadata = producer.send(new ProducerRecord<>(Config.TOPIC, "key" + i,  "number-" + i)).get();
            System.out.println(recordMetadata.partition());
        }
        producer.close();

        try {
            // 异步，不处理结果
            producer.send(record1);

            // 调用get阻塞等待结果
            producer.send(record2).get();

            // 回调函数处理异常
            producer.send(record3, (RecordMetadata recordMetadata, Exception e) -> {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    System.out.println("[INFO] 回调输出：" + recordMetadata.topic() + "-"
                            + recordMetadata.partition() + ":"
                            + recordMetadata.offset());
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}

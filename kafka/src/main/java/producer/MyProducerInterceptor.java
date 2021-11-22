package producer;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义拦截器示例：为消息添加前缀
 * @author Aurora
 * @date 2021/11/15 15:26
 */
public class MyProducerInterceptor implements ProducerInterceptor {

    private AtomicInteger sendSuccess = new AtomicInteger(0);

    private AtomicInteger sendFailure = new AtomicInteger(0);

    @Override
    public ProducerRecord onSend(ProducerRecord record) {
        String newVal = "pre-" + record.value();
        return new ProducerRecord(record.topic(), record.partition(), record.timestamp(),
                record.key(), record.value(), record.headers());
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            sendFailure.incrementAndGet();
        } else {
            sendSuccess.incrementAndGet();
            System.out.println("write into partition: " + metadata.partition() + " " + metadata.topic());
        }
    }

    @Override
    public void close() {
        double rate = (double)sendSuccess.get() / (sendSuccess.get() + sendFailure.get());
        System.out.printf("[INFO] 发送成功率=%f%s\n", rate*100, "%");
    }

    @Override
    public void configure(Map<String, ?> configs) {}
}

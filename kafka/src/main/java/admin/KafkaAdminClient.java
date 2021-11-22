package admin;

import comm.Config;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import producer.Producer;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * @author Aurora
 * @date 2021/11/18 12:41
 */
public class KafkaAdminClient {

    public static void createTopic() {
        Properties prop = new Properties();
        prop.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, Config.BROKER_LIST);
        prop.setProperty(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "30000");
        AdminClient client = AdminClient.create(prop);

        NewTopic topic = new NewTopic("test5", 3, (short)1);
        CreateTopicsResult result = client.createTopics(Collections.singleton(topic));
        
        try {
            result.all().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

    public static void main(String[] args) {
        KafkaAdminClient.createTopic();
    }
}

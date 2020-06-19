package wang.miansen.kafka.springmvc;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author miansen.wang
 * @date 2020-06-11
 */
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage(String value) {
		sendMessage(null, value);
	}
	
	public void sendMessage(String key, String value) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.sendDefault(value);
		future.addCallback(success -> {
			RecordMetadata metadata = success.getRecordMetadata();
			System.out.println("生产者发送消息成功。topic: " + metadata.topic() + ", partition: " + metadata.partition() + ", offset: " + metadata.offset());
		}, failure -> {
			System.out.println("生产者发送消息失败，原因：" + failure.getMessage());
		});
	}
}

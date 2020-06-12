package wang.miansen.kafka.springboot;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author miansen.wang
 * @date 2020-06-12
 */
@Service
public class KafkaConsumerService {

	@KafkaListener(topics = {"test01"}, groupId = "group01")
	public void onMessage1(ConsumerRecord<String, String> record) {
		System.out.println(String.format("[group01-消费者1]收到了消息。topic: %s, partition: %s, offset: %s, key: %s, value: %s",
				record.topic(), record.partition(), record.offset(), record.key(), record.value()));
	}
	
	@KafkaListener(topics = {"test01"}, groupId = "group01")
	public void onMessage2(ConsumerRecord<String, String> record) {
		System.out.println(String.format("[group01-消费者2]收到了消息。topic: %s, partition: %s, offset: %s, key: %s, value: %s",
				record.topic(), record.partition(), record.offset(), record.key(), record.value()));
	}
	
	@KafkaListener(topics = {"test01"}, groupId = "group02")
	public void onMessage3(ConsumerRecord<String, String> record) {
		System.out.println(String.format("[group02-消费者1]收到了消息。topic: %s, partition: %s, offset: %s, key: %s, value: %s",
				record.topic(), record.partition(), record.offset(), record.key(), record.value()));
	}
}

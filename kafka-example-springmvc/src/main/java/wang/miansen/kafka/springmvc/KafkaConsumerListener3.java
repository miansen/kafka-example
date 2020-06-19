package wang.miansen.kafka.springmvc;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

/**
 * @author miansen.wang
 * @date 2020-06-11
 */
public class KafkaConsumerListener3 implements MessageListener<String, String> {

	@Override
	public void onMessage(ConsumerRecord<String, String> record) {
		System.out.println(String.format("消费者3收到了消息。topic: %s, partition: %s, offset: %s, key: %s, value: %s", record.topic(),
				record.partition(), record.offset(), record.key(), record.value()));
	}

}

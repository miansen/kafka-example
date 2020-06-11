package wang.miansen.kafka.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author miansen.wang
 * @date 2020-06-11
 */
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage(String key, String value) {
		kafkaTemplate.sendDefault(key, value);
	}
}

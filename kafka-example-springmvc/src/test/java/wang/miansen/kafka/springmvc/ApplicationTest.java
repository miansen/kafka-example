package wang.miansen.kafka.springmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author miansen.wang
 * @date 2020-06-11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:kafka-producer.xml", "classpath:kafka-consumer.xml"})
public class ApplicationTest {

	@Autowired
	private KafkaProducerService producerService;
	
	@Test
	public void sendMessageTest() throws Exception {
		for (int i = 0; i < 10; i++) {
			producerService.sendMessage(Integer.toString(i), "hello kafka-" + i);
		}
	}
}

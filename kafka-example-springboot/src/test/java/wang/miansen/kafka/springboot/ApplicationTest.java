package wang.miansen.kafka.springboot;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author miansen.wang
 * @date 2020-06-12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Autowired
	private KafkaProducerService producerService;

	@Test
	public void sendMessageTest(String value) {
		for (int i = 0; i < 10; i++) {
			producerService.sendMessage(Integer.toString(i), "hello kafka-" + i + "-" + new Date().getTime());
		}
	}
}

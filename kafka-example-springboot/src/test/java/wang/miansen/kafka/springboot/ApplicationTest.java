package wang.miansen.kafka.springboot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author miansen.wang
 * @date 2020-06-12
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ApplicationTest {

	@Autowired
	private KafkaProducerService producerService;

	//@Test
	public void sendMessageTest(String value) {
		for (int i = 0; i < 10; i++) {
			producerService.sendMessage(Integer.toString(i), "hello kafka-" + i + "-" + new Date().getTime());
		}
	}
	
	@Test
	public void test01() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String url = "https://www.roothub.cn/api/user/topic";
		String jsonstr = "{\"appId\": \"123\",\"docName\": \"123456\", \"docNum\": \"123456\"}";
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", "123");
		map.put("appName", "abc");
		map.put("appNum", "456");
		HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(map, headers);
		HttpEntity<String> httpEntity2 = new HttpEntity<>(jsonstr, headers);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
		HttpStatus statusCode = responseEntity.getStatusCode();
		String body = responseEntity.getBody();
	}
}

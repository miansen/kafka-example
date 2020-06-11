package wang.miansen.example.kafka.javaclient;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author miansen.wang
 * @date 2020-05-21
 */
public class CallbackProducer {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 1.配置生产者的属性，各个属性的作用可以在这里看：http://kafka.apachecn.org/documentation.html#producerconfigs
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.197.6:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		// 2.实例化一个生产者
		KafkaProducer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 0; i < 100; i++) {
			// 3.实例化一条消息，第一个参数是主题的名称，第二个参数是消息的内容
			ProducerRecord<String,String> record = new ProducerRecord<>("test01", Integer.toString(i));
			// 4.发送消息
			producer.send(record, new Callback() {
				// 5.在回调函数里处理结果，这样就不会阻塞了
				@Override
				public void onCompletion(RecordMetadata metadata, Exception exception) {
					System.out.println("topic: " + metadata.topic() + ", partition: " + metadata.partition() + ", offset: " + metadata.offset());
				}
			});
		}
		// 6.关闭资源
		producer.close();
	}
}

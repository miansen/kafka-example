package wang.miansen.example.kafka.javaclient;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

/**
 * @author miansen.wang
 * @date 2020-05-22
 */
public class AutoCommitConsumer {

	public static void main(String[] args) {
		// 1.配置消费者的属性，各个属性的作用可以在这里看：http://kafka.apachecn.org/documentation.html#consumerconfigs
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.197.6:9092");
		props.put("group.id", "my-consumer-group-04");
		props.put("enable.auto.commit", "false");
		props.put("auto.commit.interval.ms", "1000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		// 2.实例化一个消费者
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		// 3.订阅主题（可以订阅多个）
		// consumer.subscribe(Arrays.asList("test01"));
		
		// 订阅分区（订阅主题和订阅分区是互斥的）
		TopicPartition partition0 = new TopicPartition("test01", 0);
		TopicPartition partition1 = new TopicPartition("test01", 1);
		consumer.assign(Arrays.asList(partition0, partition1));
		consumer.seek(partition0, 784);
		// 4.循环消费消息
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
			for (ConsumerRecord<String, String> record : records) {
				System.out.println(String.format("topic: %s, partition: %s, offset: %s, key: %s, value: %s",
						record.topic(), record.partition(), record.offset(), record.key(), record.value()));
			}
			consumer.commitSync();
		}
	}
}

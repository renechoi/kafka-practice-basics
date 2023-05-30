package com.example.kafkapractice;

import static com.example.kafkapractice.CreateTopicExample.*;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaPracticeApplication {

	private static final Logger log = LoggerFactory.getLogger(KafkaPracticeApplication.class.getSimpleName());

	public static void main(String[] args) {
		// SpringApplication.run(KafkaPracticeApplication.class, args);

		CreateTopicExample.createTopics();

		// createProducer();

	}

	private static void createProducer() {
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

		// 프로듀서로 문자열이 들어오면 직렬화 한다
		properties.setProperty("key.serializer", StringSerializer.class.getName());
		properties.setProperty("value.serializer", StringSerializer.class.getName());

		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

		ProducerRecord<String, String> producerRecord = new ProducerRecord<>("demo_java", "hello world");

		// 전송하는 것은 비동기이지만 테스트를 위해 flush 해주어 바로 반응을 보도록 한다.
		producer.send(producerRecord);

		producer.flush();
		producer.close();
	}



}




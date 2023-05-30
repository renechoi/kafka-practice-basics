package com.example.kafkapractice;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;

public class CreateTopicExample {

	public static void createTopics() {
		String bootstrapServers = "127.0.0.1:9092";

		// Kafka 관리자 클라이언트 설정
		Properties adminProps = new Properties();
		adminProps.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		AdminClient adminClient = AdminClient.create(adminProps);

		// 생성할 토픽 설정
		String topicName = "demo_java";
		int numPartitions = 3;
		short replicationFactor = 1;

		// NewTopic 객체 생성
		NewTopic newTopic = new NewTopic(topicName, numPartitions, replicationFactor);

		// 토픽 생성
		adminClient.createTopics(Collections.singletonList(newTopic));

		// AdminClient 종료
		adminClient.close();
	}
}

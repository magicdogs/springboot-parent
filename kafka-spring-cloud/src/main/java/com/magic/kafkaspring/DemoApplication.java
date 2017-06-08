package com.magic.kafkaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.MessageConverter;

@SpringBootApplication
@EnableBinding(KafkaTopicChannel.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@StreamListener(KafkaTopicChannel.TEST)
	public void processVote(Message vote) {
		//votingService.recordVote(vote);
		String str = (String) vote.getPayload();
		System.out.println(vote);
	}

	@Bean
	public MessageConverter ms(){
		return new MessageConvert();
	}
}

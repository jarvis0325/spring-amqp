package com.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitmqConfig {

	public RabbitmqConfig() {
		super();
		System.setProperty("spring.amqp.deserialization.trust.all", "true");
	}

	static String OutQueue = "OutQueue";

	@Bean
	public Queue inQueue() {
		return new Queue("InQueue", false);
	}

	@Bean
	public Queue outQueue() throws UnknownHostException {
		OutQueue = OutQueue + "-" +InetAddress.getLocalHost() ;
		return new Queue(OutQueue, false);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setReplyAddress(OutQueue);
		rabbitTemplate.setUserCorrelationId(true);
		return rabbitTemplate;
	}

	@Bean
	public SimpleMessageListenerContainer replyListenerContainer(CachingConnectionFactory connectionFactory,
			RabbitTemplate rabbitTemplate) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(OutQueue);
		container.setReceiveTimeout(30000);
		container.setMessageListener(rabbitTemplate);
		return container;
	}

}

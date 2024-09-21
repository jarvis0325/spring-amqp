package com.test;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReceiveMessageListener {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RabbitListener(queues = { "InQueue" })
	public void receive(TestVo testVo, Message message, @Header(AmqpHeaders.CORRELATION_ID) String correlationId,
			@Header(AmqpHeaders.REPLY_TO) String replyTo) {
		log.info("testVo:{},correlationId:{},replyTo:{} , message:{}", testVo, correlationId, replyTo, message);
		testVo.setTag(testVo.getTag() + 1000);

		rabbitTemplate.convertAndSend(replyTo, testVo, messagep -> {
			messagep.getMessageProperties().setCorrelationId(correlationId);
			return messagep;
		});
	}

}

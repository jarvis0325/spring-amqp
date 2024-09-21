package com.test;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Test
	void test1() {
		TestVo testVo1 = new TestVo();
		testVo1.setName("GGG");

		TestVo testVo2 = (TestVo) rabbitTemplate.convertSendAndReceive("InQueue", testVo1, messagep -> {
			messagep.getMessageProperties().setCorrelationId(UUID.randomUUID().toString());
			return messagep;
		});

		log.info("testVo2:{}", testVo2);
	}

}

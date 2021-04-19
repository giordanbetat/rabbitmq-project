package com.rabbitmq.producer;

import com.rabbitmq.entity.DummyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingleActiveProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendDummy() {
		for (int i = 0; i < 10_000; i++) {
			var message = new DummyMessage("Message " + i, i);
			rabbitTemplate.convertAndSend("x.single", "", message);
		}
	}
}

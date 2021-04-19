package com.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitmq.entity.DummyMessage;
import com.rabbitmq.producer.AnotherDummyProducer;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	private AnotherDummyProducer producer;

	@Override
	public void run(String... args) throws Exception {
		var dummyMessage = new DummyMessage("Just a dummy message", 1);
		producer.sendDummy(dummyMessage);
	}

}

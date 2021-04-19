package com.rabbitmq.consumer;

import com.rabbitmq.entity.DummyMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Service
@Log4j2
public class DummyConsumer {

  @RabbitListener(queues = "q.dummy")
  public void listenDummy(DummyMessage message) {
    log.info("Message is {}", message);
  }

}

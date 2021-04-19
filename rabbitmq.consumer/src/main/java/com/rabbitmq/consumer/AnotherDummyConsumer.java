package com.rabbitmq.consumer;

import com.rabbitmq.entity.DummyMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AnotherDummyConsumer {

  @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "q.auto-dummy", durable = "true")
      , exchange = @Exchange(name = "x.auto-dummy", type = ExchangeTypes.DIRECT, durable = "true")
      , key = "routing-key", ignoreDeclarationExceptions = "true"))
  public void listenDummy(DummyMessage message) {
    log.info("Message is {}", message);
  }

}

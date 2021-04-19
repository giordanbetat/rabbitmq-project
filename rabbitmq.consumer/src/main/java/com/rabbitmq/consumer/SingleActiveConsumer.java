package com.rabbitmq.consumer;

import com.rabbitmq.entity.DummyMessage;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Service
@Log4j2
public class SingleActiveConsumer {

  @RabbitListener(queues = "q.single", concurrency = "5")
  public void listenDummy(DummyMessage message) throws InterruptedException {
    log.info("Consuming {}", message);
    TimeUnit.SECONDS.sleep(1);
  }

}

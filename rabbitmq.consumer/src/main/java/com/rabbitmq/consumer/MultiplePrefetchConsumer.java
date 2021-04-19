package com.rabbitmq.consumer;

import com.rabbitmq.entity.DummyMessage;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Service
@Log4j2
public class MultiplePrefetchConsumer {

  @RabbitListener(queues = "q.transaction", concurrency = "2")
  public void listenTransaction(DummyMessage message) throws InterruptedException {
    log.info("Taking transaction {}", message);
    TimeUnit.MILLISECONDS.sleep(100);
  }

  @RabbitListener(queues = "q.scheduler", concurrency = "2", containerFactory = "prefetchOneContainerFactory")
  public void listenScheduler(DummyMessage message) throws InterruptedException {
    log.info("Taking scheduler {}", message);
    TimeUnit.MINUTES.sleep(1);
  }

}

package com.rabbitmq.scheduler;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@Log4j2
public class RabbitMqScheduler {

  @Autowired
  private RabbitListenerEndpointRegistry registry;

  @Scheduled(cron = "0 6 8 * * *")
  public void stopAll() {
    registry.getListenerContainers().forEach(c -> {
      log.info("Stopping listener container {}", c);
      c.stop();
    });
  }

  @Scheduled(cron = "0 8 8 * * *")
  public void startAll() {
    registry.getListenerContainers().forEach(c -> {
      log.info("Starting listener container {}", c);
      c.start();
    });
  }

}

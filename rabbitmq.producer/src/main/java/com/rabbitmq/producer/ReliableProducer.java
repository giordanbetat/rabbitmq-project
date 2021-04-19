package com.rabbitmq.producer;

import com.rabbitmq.entity.DummyMessage;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ReliableProducer {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @PostConstruct
  private void registerCallback() {
    rabbitTemplate.setConfirmCallback((correlation, ack, reason) -> {
      if (correlation == null) {
        return;
      }

      if (ack) {
        log.info("Message with correlation {} published", correlation.getId());
      } else {
        log.warn("Invalid exchange for message with correlation {}", correlation.getId());
      }
    });

    rabbitTemplate.setReturnsCallback(returned -> {
      log.info("return callback");

      if (returned.getReplyText() != null && returned.getReplyText().equalsIgnoreCase("NO_ROUTE")) {
        var id = returned.getMessage().getMessageProperties()
            .getHeader("spring_returned_message_correlation")
            .toString();
        log.warn("Invalid routing key for message {}", id);
      }
    });
  }

  public void sendDummyWithInvalidRoutingKey(DummyMessage message) {
    var correlationData = new CorrelationData(Integer.toString(message.getPublishOrder()));
    rabbitTemplate.convertAndSend("x.dummy2", "invalid-routing-key", message, correlationData);
  }

  public void sendDummyToInvalidExchange(DummyMessage message) {
    var correlationData = new CorrelationData(Integer.toString(message.getPublishOrder()));
    rabbitTemplate.convertAndSend("x.non-exists-exchange", "", message, correlationData);
  }

}

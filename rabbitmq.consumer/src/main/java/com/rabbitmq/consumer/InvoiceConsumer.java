package com.rabbitmq.consumer;

import com.rabbitmq.entity.InvoiceCancelledMessage;
import com.rabbitmq.entity.InvoiceCreatedMessage;
import com.rabbitmq.entity.InvoicePaidMessage;
import com.rabbitmq.entity.PaymentCancelStatus;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.messaging.handler.annotation.SendTo;

//@Service
//@RabbitListener(queues = "q.invoice")
@Log4j2
public class InvoiceConsumer {

  @RabbitHandler
  public void handleInvoiceCreated(InvoiceCreatedMessage message) {
    log.info("Invoice created : {}", message);
  }

  @RabbitHandler
  public void handleInvoicePaid(InvoicePaidMessage message) {
    log.info("Invoice paid : {}", message);
  }

  @RabbitHandler(isDefault = true)
  public void handleDefault(Object message) {
    log.warn("Handling default : {}", message);
  }

  @RabbitHandler
  @SendTo("x.invoice.cancel/")
  public PaymentCancelStatus handleInvoiceCancelled(InvoiceCancelledMessage message) {
    var randomStatus = ThreadLocalRandom.current().nextBoolean();

    return new PaymentCancelStatus(randomStatus, LocalDate.now(), message.getInvoiceNumber());
  }

}

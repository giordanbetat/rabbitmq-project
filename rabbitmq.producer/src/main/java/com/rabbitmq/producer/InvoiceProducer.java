package com.rabbitmq.producer;

import com.rabbitmq.entity.InvoiceCancelledMessage;
import com.rabbitmq.entity.InvoiceCreatedMessage;
import com.rabbitmq.entity.InvoicePaidMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	private static final String EXCHANGE = "x.invoice";

	public void sendInvoiceCreated(InvoiceCreatedMessage message) {
		rabbitTemplate.convertAndSend(EXCHANGE, message.getInvoiceNumber(), message);
	}

	public void sendInvoicePaid(InvoicePaidMessage message) {
		rabbitTemplate.convertAndSend(EXCHANGE, message.getInvoiceNumber(), message);
	}

	public void sendInvoiceCancelled(InvoiceCancelledMessage message) {
		rabbitTemplate.convertAndSend(EXCHANGE, message.getInvoiceNumber(), message);
	}

}

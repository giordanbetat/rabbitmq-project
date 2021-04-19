package com.rabbitmq.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InvoiceCreatedMessage {

	private double amount;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;

	private String currency;

	private String invoiceNumber;
}

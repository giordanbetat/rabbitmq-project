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
public class InvoiceCancelledMessage {

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate cancelDate;
	
	private String invoiceNumber;
	
	private String reason;
}

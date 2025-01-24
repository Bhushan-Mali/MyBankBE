package com.example.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsResponse {

	private Long transactionId;
	private String transactionDate;
	private Double amount;
	private Double balanceAfter;
}

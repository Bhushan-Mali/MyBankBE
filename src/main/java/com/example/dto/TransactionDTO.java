package com.example.dto;

import com.example.enums.TransactionStatus;
import com.example.enums.TransactionType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {

	private Long transactionId;
	private String accountNumber;
    private TransactionType type; 
    private String transactionDate; 
    private Long accountId;
    //private String accountNumber; // To reference the account
    private String customerName; // To include customer details
    private Double amount;
    private String description;
    private Double balanceAfter;
    private TransactionStatus status; // Can use String or Enum (e.g., TransactionStatus)

}

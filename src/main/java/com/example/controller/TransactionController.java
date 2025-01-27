package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.TransactionDTO;
import com.example.model.Transaction;
import com.example.response.CommonResponse;
import com.example.response.TransactionsResponse;
import com.example.service.TransactionService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/transaction")
@Tag(name="Transaction Management", description = "Operations related to transaction")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/credit")
	public ResponseEntity<?> creditMoney(@RequestBody TransactionDTO transactiondto) {
		Transaction transaction = transactionService.creditMoney(transactiondto);
		TransactionsResponse transactionResponse = new TransactionsResponse();
		CommonResponse response = new CommonResponse();
		if (transaction != null) {
			transactionResponse.setTransactionId(transaction.getTransactionId());
			transactionResponse.setBalanceAfter(transaction.getBalanceAfter());
			transactionResponse.setTransactionDate(transaction.getTransactionDate());
			transactionResponse.setAmount(transaction.getAmount());
			return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
		} 
		response.setMessage("Something went wrong");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<?> withdrawMoney(@RequestBody TransactionDTO transactiondto) {
		Transaction transaction = transactionService.withdrawMoney(transactiondto);
		TransactionsResponse transactionResponse = new TransactionsResponse();
		CommonResponse response = new CommonResponse();
		if (transaction != null) {
			transactionResponse.setTransactionId(transaction.getTransactionId());
			transactionResponse.setBalanceAfter(transaction.getBalanceAfter());
			transactionResponse.setTransactionDate(transaction.getTransactionDate());
			transactionResponse.setAmount(transaction.getAmount());
			return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
		}
		response.setMessage("Something went wrong");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}

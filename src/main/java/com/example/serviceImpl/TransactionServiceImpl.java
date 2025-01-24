package com.example.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.TransactionDTO;
import com.example.enums.TransactionStatus;
import com.example.enums.TransactionType;
import com.example.model.Account;
import com.example.model.Transaction;
import com.example.repository.AccountRepository;
import com.example.repository.TransactionRepository;
import com.example.service.TransactionService;

import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	@Transactional
	public Transaction creditMoney(TransactionDTO transactiondto) {
		Account account = accountRepository.getByAccountNumber(transactiondto.getAccountNumber());		
		Transaction transaction = new Transaction();
		if(account != null) {	
			if (transactiondto.getAmount() > 0) {	
				transaction.setDescription(transactiondto.getDescription());
				LocalDateTime currentDateTime = LocalDateTime.now();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedDateTime = currentDateTime.format(formatter);
				transaction.setTransactionDate(formattedDateTime);
				transaction.setBalanceBefore(account.getBalance());
				transaction.setType(TransactionType.DEPOSIT);
				transaction.setStatus(TransactionStatus.COMPLETED);
				transaction.setAmount(transactiondto.getAmount());	
				transaction.setAccount(account);
				transactionRepository.save(transaction);
				Double currentBalance = account.getBalance();
				Double creditAmmount = transactiondto.getAmount();
				account.setBalance(currentBalance + creditAmmount);
				transaction.setBalanceAfter(currentBalance + creditAmmount);
				transaction.setCustomer(account.getCustomer());
		 		accountRepository.save(account);
				return transaction;			
			} else {
				logger.error("Amount must be greater than zero");
				throw new IllegalArgumentException("Amount must be greater than zero");
			}			
		} 
		logger.error("Account not found");
		throw new IllegalArgumentException("Account not found");		
	}

	@Override
	@Transactional
	public Transaction withdrawMoney(TransactionDTO transactiondto) {
		Account account = accountRepository.getByAccountNumber(transactiondto.getAccountNumber());	
		Transaction transaction = new Transaction();
		Double withdrawAmount = transactiondto.getAmount();
		if (account != null) {	
			Double balance = account.getBalance();
			if (withdrawAmount <= 0) {
				logger.error("Withdrawal amount must be greater than zero");
	            throw new IllegalArgumentException("Withdrawal amount must be greater than zero");
	        }
	        if (withdrawAmount >= balance) {
	        	logger.error("Insufficient balance");
	            throw new IllegalStateException("Insufficient balance");
	        }
	        if ((balance - withdrawAmount) < account.getMinBalance()) {
	        	logger.error("Withdrawal would violate the minimum balance requirement of " + account.getMinBalance());
	            throw new IllegalStateException("Withdrawal would violate the minimum balance requirement of " + account.getMinBalance());
	        }	        
	        transaction.setDescription(transactiondto.getDescription());
			LocalDateTime currentDateTime = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedDateTime = currentDateTime.format(formatter);
			transaction.setTransactionDate(formattedDateTime);
			transaction.setBalanceBefore(account.getBalance());
			transaction.setType(TransactionType.WITHDRAWAL);
			transaction.setStatus(TransactionStatus.COMPLETED);
			transaction.setAmount(transactiondto.getAmount());
			transaction.setAccount(account);
			transaction.setCustomer(account.getCustomer());
			transaction.setBalanceAfter(balance - withdrawAmount);
			transactionRepository.save(transaction);			
			account.setBalance(transaction.getBalanceAfter()); 
			accountRepository.save(account);
		} else {
			logger.error("Account not found");
			throw new IllegalStateException("Account not found");
		}
		return transaction;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

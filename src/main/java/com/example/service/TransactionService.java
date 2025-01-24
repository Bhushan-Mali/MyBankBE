package com.example.service;

import com.example.dto.TransactionDTO;
import com.example.model.Transaction;

public interface TransactionService {

	Transaction creditMoney(TransactionDTO transactiondto);

	Transaction withdrawMoney(TransactionDTO transactiondto);

}

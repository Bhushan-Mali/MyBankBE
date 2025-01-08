package com.example.service;

import java.util.List;

import com.example.dto.AccountDTO;
import com.example.model.Account;


public interface AccountService {

	Account addAccount(AccountDTO accountDto, Long customerId);
	List<Account> getAllAccounts();
	Account getAccountById(Long accountId);
	Account updateAccount(Long accountId, Account account);

}

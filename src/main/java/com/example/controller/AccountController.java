package com.example.controller;

import com.example.dto.AccountDTO;
import com.example.model.Account;
import com.example.response.CommonResponse;
import com.example.service.AccountService;
import com.example.serviceImpl.AccountServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountServiceImpl accountServiceImpl;


    @PostMapping("/createAccount/{customerId}")
    public ResponseEntity<CommonResponse> createAccount(@RequestBody AccountDTO accountDto, @PathVariable Long customerId){
        Account account = accountService.addAccount(accountDto, customerId);
        CommonResponse response = new CommonResponse();
        if (account != null) {	        
	        response.setMessage("Account successfully created");
	        response.setAccountId(account.getId());
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			response.setMessage("Something went wrong");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
        
    }    
    
    @GetMapping("")
    public List<Account> getAccounts() {
    	return accountService.getAllAccounts();
    }    
    
    @GetMapping("/{accountId}")
	public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
		Account account = accountService.getAccountById(accountId);
		CommonResponse response = new CommonResponse();
		if(account != null) {
			return new ResponseEntity<>(account, HttpStatus.FOUND);
		}
		response.setMessage("Account not found with given id");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
    
    @PutMapping("/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Long accountId, @RequestBody AccountDTO accountDto) {
        Account account1 = accountService.updateAccount(accountId, accountDto);  
        CommonResponse response = new CommonResponse();
        if(account1 != null) {
        	response.setMessage("Account updated successully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		response.setMessage("Something went wrong");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    
    
    
    
    
    
    
    
    
    

}

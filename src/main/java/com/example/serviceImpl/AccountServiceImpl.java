package com.example.serviceImpl;

import com.example.dto.AccountDTO;
import com.example.model.Account;
import com.example.model.Customer;
import com.example.repository.AccountRepository;
import com.example.repository.CustomerRepository;
import com.example.service.AccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    CustomerRepository customerRepository;

    
    
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private static final String PARENTBANK_CODE = "973081";
    private static final String BRANCH_CODE = "7771";
    
    
    //method to add new account
    public Account addAccount(AccountDTO accountDto, Long customerId){
        Account account = new Account();        
        Customer customer = customerRepository.findCustomerById(customerId);        
        if(customer != null) {
           account.setCustomer(customer);
           account.setAccountType(accountDto.getAccountType());
           account.setAccountNumber(PARENTBANK_CODE + BRANCH_CODE + this.generateRandomNumber());
           account.setBalance(accountDto.getBalance());
           LocalDateTime currentDateTime = LocalDateTime.now();
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
           String formattedDateTime = currentDateTime.format(formatter);
           account.setCreatedDate(formattedDateTime);
           account.setStatus(accountDto.getStatus());
           accountRepository.save(account);
           return account;
        }
        return null;
    }
	
    @Override
	public List<Account> getAllAccounts() {
    	List<Account> accountList = accountRepository.findAll();
		if(accountList != null && !accountList.isEmpty()) {
			return accountList;
		}
		return null;
	}
    
    
	@Override
	public List<AccountDTO> getAllAccountDtos() {
		List<Account> accountList = accountRepository.findAll();
		List<AccountDTO>  accountDto = accountList.stream().map(this::convertAccountToAccountDto).collect(Collectors.toList());
		return accountDto;
	}
	
	
	//converts Account entity to AccountDTO
	public AccountDTO convertAccountToAccountDto(Account account) {
		AccountDTO accountdto = new AccountDTO();
		accountdto.setAccountNumber(account.getAccountNumber());
		accountdto.setAccountType(account.getAccountType());
		accountdto.setBalance(account.getBalance());
		accountdto.setCreatedDate(account.getCreatedDate());
		accountdto.setStatus(account.getStatus());
		return accountdto;
	}
	
	@Override
	public Account getAccountById(Long accountId) {
		return accountRepository.getAccountById(accountId);
	}	
	
	@Override
	public Account updateAccount(Long accountId, AccountDTO accountDto) {
		Account account1 = accountRepository.getAccountById(accountId);		
		if (account1 != null) {
			account1.setStatus(accountDto.getStatus());
			accountRepository.save(account1);
			return account1;
		}
		return null;
	}
	
    
    //method which returns 4 digit auto generated random number between range of 1000 to 9999
    public String generateRandomNumber(){
        Random random = new Random();
        String randomNumner = String.valueOf(random.nextInt(1000, 9999));   //return String.valueOf(random.nextInt(1000, 9999));
        return randomNumner;
    }

	

	





}

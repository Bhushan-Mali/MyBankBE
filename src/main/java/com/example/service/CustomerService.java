package com.example.service;

import java.util.List;

import com.example.dto.CustomerDTO;
import com.example.model.Customer;

public interface CustomerService {

	Customer addCustomer(CustomerDTO customerDto);

	Customer getCustomerById(Long customerId);

	List<Customer> getAllCustomers();

	Customer getCustomerByEmail(String email);

	//Customer getCustomerByName(String customerName);

}

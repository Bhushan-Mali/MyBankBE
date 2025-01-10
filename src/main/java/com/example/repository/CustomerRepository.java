package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findCustomerById(Long customerId);

	Customer findByEmail(String email);

	Customer getCustomerById(Long customerId);

	//void getCustomerByName(String customerName);

}

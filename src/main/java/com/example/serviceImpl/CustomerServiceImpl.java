package com.example.serviceImpl;

import com.example.dto.CustomerDTO;
import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer addCustomer(CustomerDTO customerDto) {
		Customer customer = new Customer();
		Customer customer1 = customerRepository.findByEmail(customerDto.getEmail());
		
		if (customer1 == null) {
			customer.setAadhaarNumber(customerDto.getAadhaarNumber());
			customer.setBirthdate(customerDto.getBirthdate());
			customer.setContactNumber(customerDto.getContactNumber());
			customer.setDistrict(customerDto.getDistrict());
			customer.setEmail(customerDto.getEmail());
			customer.setFirstName(customerDto.getFirstName());
			customer.setLastName(customerDto.getLastName());
			LocalDateTime currentDateTime = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedDateTime = currentDateTime.format(formatter);
			customer.setRegistrationDate(formattedDateTime);
			customer.setState(customerDto.getState());
			customer.setTaluka(customerDto.getTaluka());
			customer.setVillage(customerDto.getVillage());
			customerRepository.save(customer);
			return customer;
		}
		return null;
	}

	@Override
	public Customer getCustomerById(Long customerId) {
		return customerRepository.getCustomerById(customerId);
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = customerRepository.findAll();
		if(customerList != null && !customerList.isEmpty()) {
			return customerList;
		}
		return null;
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

//	@Override
//	public Customer getCustomerByName(String customerName) {
//		customerRepository.getCustomerByName(customerName);
//		return null;
//	}


}

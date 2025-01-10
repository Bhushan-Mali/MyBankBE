package com.example.controller;

import com.example.dto.CustomerDTO;
import com.example.model.Customer;
import com.example.response.CommonResponse;
import com.example.service.CustomerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @PostMapping("/addCustomer")
    public ResponseEntity<CommonResponse> registerCustomer(@RequestBody CustomerDTO customerDto){
        Customer customer = customerService.addCustomer(customerDto);
        CommonResponse response = new CommonResponse();
        if (customer != null) {
			response.setMessage("Registration successfull");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			response.setMessage("This email is in use. Please try with different email");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
    }
    
    
    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable Long customerId) {
    	Customer customer = customerService.getCustomerById(customerId);
    	if (customer != null) {
			return new ResponseEntity<>(customer, HttpStatus.FOUND);
		} else {
	    	CommonResponse response = new CommonResponse();
	    	response.setMessage("Customer not found with given id");
	    	return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
    }
    
    @GetMapping("/email")
    public ResponseEntity<?> getCustomer(@RequestParam String email) {
    	Customer customer = customerService.getCustomerByEmail(email);
    	if (customer != null) {
    		return new ResponseEntity<>(customer, HttpStatus.FOUND);
		} else {
	    	CommonResponse response = new CommonResponse();
	    	response.setMessage("Customer not found with given email");
	    	return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);	
		}
    }
    
    @GetMapping("")
    public List<Customer> getCustomers() {
    	return customerService.getAllCustomers();      	
    }
    
//    @GetMapping("/{customerName}")
//    public void getCustomer(@PathVariable String customerName) {
//    	customerService.getCustomerByName(customerName);
//    }
}

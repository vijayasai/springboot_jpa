package com.demo.jpa.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.jpa.entity.Customer;
import com.demo.jpa.service.CustomerService;

@RestController
public class CustomerController {

	private static final Logger LOGGER =  LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	

	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		MDC.put("reference", "test");
		LOGGER.info("Got it");
		return customerService.getAllCustomers();
	}

//	@GetMapping("/customers/{id}")
//	public Customer getCustomerByIdT(@PathVariable(value = "id") Long customerId) throws Exception {
//		//return  customerService.getCustomerById(customerId);
//		
//		return this.customerRepository.findById(customerId).orElseThrow(() -> new Exception("User not found with id :" + customerId));
//	}
	
	@GetMapping("/customers/{id}")
	public Optional<Customer> getCustomerById(@PathVariable(value = "id") Long customerId) {
		return  customerService.getCustomerById(customerId);
	}

	@PostMapping("/customers")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
			@RequestBody Customer customerDetails) {
		return customerService.updateCustomer(customerId, customerDetails);
	}

	@DeleteMapping("/customers/{id}")
	public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId) {
		return customerService.deleteCustomer(customerId);
	}
}

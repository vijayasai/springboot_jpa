package com.demo.jpa.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.jpa.entity.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomers();

	public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable(value = "id") Long customerId);

	public Customer createCustomer(@RequestBody Customer customer);

	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
			@RequestBody Customer customerDetails);

	public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId);
}

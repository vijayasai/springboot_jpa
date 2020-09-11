package com.demo.jpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.demo.jpa.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable(value = "id") Long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		return ResponseEntity.ok().body(customer);
	}

	@PostMapping("/customers")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
			@RequestBody Customer customerDetails) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		customer.get().setLastName(customerDetails.getLastName());
		customer.get().setFirstName(customerDetails.getFirstName());
		final Customer updatedCustomer = customerRepository.save(customer.get());
		return ResponseEntity.ok(updatedCustomer);
	}

	@DeleteMapping("/customers/{id}")
	public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		customerRepository.delete(customer.get());
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}

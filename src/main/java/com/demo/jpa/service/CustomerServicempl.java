package com.demo.jpa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.jpa.entity.Customer;
import com.demo.jpa.exception.APIException;
import com.demo.jpa.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServicempl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomers() {
		System.out.println("Here in customers controller");
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(Long customerId)  {
		return Optional.of(customerRepository.findById(customerId).orElseThrow(() -> new APIException("User not found with id :" + customerId)));
		//return ResponseEntity.ok().body(customer);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public ResponseEntity<Customer> updateCustomer(Long customerId, Customer customerDetails) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		customer.get().setLastName(customerDetails.getLastName());
		customer.get().setFirstName(customerDetails.getFirstName());
		customer.get().setEmailId(customerDetails.getEmailId());
		final Customer updatedCustomer = customerRepository.save(customer.get());
		return ResponseEntity.ok(updatedCustomer);
	}

	@Override
	public Map<String, Boolean> deleteCustomer(Long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		customerRepository.delete(customer.get());
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}

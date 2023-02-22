package com.test.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.dtos.CustomerDto;
import com.test.demo.entities.Customer;
import com.test.demo.services.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// Get All Customer List
	@GetMapping
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	// Save New Customer Details
	@PostMapping
	public Customer createCustomer(@RequestBody CustomerDto customerDto) {
		return customerService.createCustomer(customerDto);
	}

	// Get Customer Details By Id
	@GetMapping("/{id}")
	public Customer getByCustomerId(@PathVariable long id) {
		return customerService.getByCustomerId(id);
	}

	// Update Customer By Id
	@PutMapping("/{id}")
	public Customer updateCustomer(@PathVariable long id, @RequestBody CustomerDto customerDto) {
		return customerService.updateCustomer(id, customerDto);
	}

	// Delete Customer By Id
	@DeleteMapping("/{id}")
	public String deleteCustomer(@PathVariable long id) {
		return customerService.deleteCustomer(id);
	}

}

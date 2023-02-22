package com.app.easyrides.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.easyrides.dtos.PagedResponseDto;
import com.app.easyrides.entities.Customer;
import com.app.easyrides.services.CustomerService;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<PagedResponseDto<Customer>> getCustomerList(
			@QuerydslPredicate(root = Customer.class) Predicate predicate, Pageable pageable) {
		log.info(">> getCustomerList({}, {})", predicate, pageable);
		return ResponseEntity.ok(customerService.getCustomerList(predicate, pageable));
	}

	@PostMapping
	public ResponseEntity<Void> createCustomer(@RequestBody Customer customerDto) {
		log.info(">> createCustomer({})", customerDto);
		customerService.createCustomer(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		log.info(">> getCustomerById({})", id);
		return ResponseEntity.ok(customerService.getCustomerById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDto) {
		log.info(">> updateCustomer({}, {})", id, customerDto);
		customerService.updateCustomer(id, customerDto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		log.info(">> deleteCustomer({})", id);
		customerService.deleteCustomer(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}

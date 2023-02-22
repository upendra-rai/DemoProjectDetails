package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.CustomerRequestDto;
import com.demo.dto.CustomerResponseDto;
import com.demo.dto.PagedResponseDto;
import com.demo.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public ResponseEntity<List<CustomerResponseDto>> getCustomer() {
		return ResponseEntity.ok(customerService.getAll());
	}
	@PostMapping
	public ResponseEntity<Void>saveCustomer(@RequestBody CustomerRequestDto customerRequestDto){
		customerService.saveCustomer(customerRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerResponseDto>getCustomerById(@PathVariable Long customerId){
		return ResponseEntity.ok(customerService.findCustomerById(customerId));
	}
	
	@PutMapping("/{CustomerId}")
	public ResponseEntity<Void>updateCustomer(@PathVariable Long CustomerId,@RequestBody CustomerRequestDto customerRequestDto){
	    customerService.updateCustomer(CustomerId, customerRequestDto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	@GetMapping
	public ResponseEntity<PagedResponseDto<CustomerResponseDto>>getAllCustomers(Pageable pageable){
		return ResponseEntity.ok(customerService.getAllCustomers(pageable));
	}
	
	
	
}

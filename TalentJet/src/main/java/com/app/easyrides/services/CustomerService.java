package com.app.easyrides.services;

import org.springframework.data.domain.Pageable;

import com.app.easyrides.dtos.PagedResponseDto;
import com.app.easyrides.entities.Customer;
import com.querydsl.core.types.Predicate;

public interface CustomerService {
	PagedResponseDto<Customer> getCustomerList(Predicate predicate, Pageable pageable);
	
	void createCustomer(Customer customerDto);

	Customer getCustomerById(Long id);

	void updateCustomer(Long id, Customer customerDto);

	void deleteCustomer(Long id);

}

package com.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.demo.dto.CustomerRequestDto;
import com.demo.dto.CustomerResponseDto;
import com.demo.dto.PagedResponseDto;

public interface CustomerService {
	
	public List<CustomerResponseDto>getAll();
	public void saveCustomer(CustomerRequestDto customerRequestDto);
	public CustomerResponseDto findCustomerById(Long customerId);
	public void updateCustomer(Long customerId, CustomerRequestDto customerRequestDto);
	
	public PagedResponseDto<CustomerResponseDto> getAllCustomers(Pageable pageable);

}

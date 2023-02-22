package com.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.dao.CustomerDAO;
import com.demo.dto.CustomerRequestDto;
import com.demo.dto.CustomerResponseDto;
import com.demo.dto.PagedResponseDto;
import com.demo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public List<CustomerResponseDto> getAll() {
		return customerDAO.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	private CustomerResponseDto convertEntityToDto(Customer customer) {
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		customerResponseDto.setId(customer.getId());
		customerResponseDto.setName(customer.getName());
		customerResponseDto.setNumber(customer.getNumber());
		customerResponseDto.setCity(customer.getCity());
		return customerResponseDto;

	}

	@Override
	public void saveCustomer(CustomerRequestDto customerRequestDto) {
		customerDAO.save(maptoEntity(customerRequestDto));
	}

	private Customer maptoEntity(CustomerRequestDto customerRequestDto) {
		Customer customer = new Customer();
		customer.setName(customerRequestDto.getName());
		customer.setNumber(customerRequestDto.getNumber());
		customer.setCity(customerRequestDto.getCity());
		return customer;
	}

	@Override
	public CustomerResponseDto findCustomerById(Long customerId) {
		Customer customer=customerDAO.findById(customerId).orElseThrow(()->new RuntimeException("Customer not found"));
		return convertEntityToDto(customer);
	}

	@Override
	public void updateCustomer(Long customerId, CustomerRequestDto customerRequestDto) {
		Customer customer=customerDAO.findById(customerId).orElseThrow(()->new RuntimeException("CustomerId not found"));
		customer.setName(customerRequestDto.getName());
		customer.setNumber(customerRequestDto.getNumber());
		customer.setCity(customerRequestDto.getCity());
		customerDAO.save(customer);
	}

	@Override
	public PagedResponseDto<CustomerResponseDto> getAllCustomers(Pageable pageable) {
		Page<Customer>pageDto=customerDAO.findAll(pageable);
		var customerDto= new PagedResponseDto<CustomerResponseDto>();
		customerDto.setPage(pageDto.getNumber());
		customerDto.setPageSize(pageDto.getSize());
		customerDto.setTotalCount(pageDto.getTotalElements());
		customerDto.setList(pageDto.getContent().stream().map(this::convertEntityToDto).collect(Collectors.toList()));
		return customerDto;
	}

}

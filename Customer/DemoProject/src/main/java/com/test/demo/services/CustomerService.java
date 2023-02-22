package com.test.demo.services;

import java.util.List;

import com.test.demo.dtos.CustomerDto;
import com.test.demo.entities.Customer;

public interface CustomerService {

	List<Customer> getCustomers();

	Customer createCustomer(CustomerDto customerDto);

	Customer getByCustomerId(long id);

	Customer updateCustomer(long id, CustomerDto customerDto);

	String deleteCustomer(long id);

}

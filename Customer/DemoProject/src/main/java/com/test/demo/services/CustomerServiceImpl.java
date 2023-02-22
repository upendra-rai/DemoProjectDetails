package com.test.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.dao.CustomerCategoryDao;
import com.test.demo.dao.CustomerDao;
import com.test.demo.dtos.CustomerDto;
import com.test.demo.entities.Customer;
import com.test.demo.entities.CustomerCategory;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private CustomerCategoryDao customerCategoryDao;

	// Get All Customer List
	@Override
	public List<Customer> getCustomers() {
		return customerDao.findAll();
	}

	// Save New Customer Details
	@Override
	public Customer createCustomer(CustomerDto customerDto) {
		Customer customer = new Customer();
		CustomerCategory customerCategory = customerCategoryDao.findById(customerDto.getCustomerCategory()).get();
		customer.setCustomerCategory(customerCategory);
		customer.setCustomerName(customerDto.getCustomerName());
		customer.setAddress(customerDto.getAddress());
		customer.setGender(customerDto.getGender());
		customer.setMobileNumber(customerDto.getMobileNumber());
		customer.setStatus(customerDto.getStatus());
		return customerDao.save(customer);
	}

	// Get Customer Details By Id
	@Override
	public Customer getByCustomerId(long id) {
		Customer customer = customerDao.findById(id).get();
		return customer;
	}

	// Update Customer By Id
	@Override
	public Customer updateCustomer(long id, CustomerDto customerDto) {
		Customer customer = customerDao.findById(id).get();
		CustomerCategory customerCategory = customerCategoryDao.findById(customerDto.getCustomerCategory()).get();
		customer.setCustomerCategory(customerCategory);
		customer.setCustomerName(customerDto.getCustomerName());
		customer.setAddress(customerDto.getAddress());
		customer.setGender(customerDto.getGender());
		customer.setMobileNumber(customerDto.getMobileNumber());
		customer.setStatus(customerDto.getStatus());
		return customer;
	}

	// Delete Customer By Id
	@Override
	public String deleteCustomer(long id) {
		Customer customer = customerDao.findById(id).get();
		customerDao.delete(customer);
		return "Customer Deleted";

	}

}

package com.app.easyrides.services;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.easyrides.dtos.PagedResponseDto;
import com.app.easyrides.entities.Address;
import com.app.easyrides.entities.Customer;
import com.app.easyrides.exception.ErrorCode;
import com.app.easyrides.exception.ServiceException;
import com.app.easyrides.repositories.AddressRepository;
import com.app.easyrides.repositories.CustomerRepository;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	@Transactional
	public PagedResponseDto<Customer> getCustomerList(Predicate predicate, Pageable pageable) {
		log.info(">> getCustomerList({}, {})", predicate, pageable);
		Page<Customer> customerPage = customerRepository.findAll(predicate, pageable);
		return PagedResponseDto.<Customer>builder()
				.list(customerPage.stream().map(customer -> customer).collect(Collectors.toList()))
				.page(customerPage.getNumber()).size(customerPage.getSize())
				.totalElements(customerPage.getTotalElements()).build();
	}

	@Override
	public void createCustomer(Customer customer) {
		log.info(">> createCustomer({})", customer);
		Address address = addressRepository.save(customer.getAddress());
		customer.setAddress(address);
		customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(Long id) {
		log.info(">> getCustomerById({})", id);
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ServiceException(ErrorCode.CUSTOMER_NOT_FOUND, id));
		return customer;
	}

	@Override
	public void updateCustomer(Long id, Customer customer) {
		log.info(">> updateCustomer({}, {})", id,customer);
		Customer updateCustomer = customerRepository.findById(id)
				.orElseThrow(() -> new ServiceException(ErrorCode.CUSTOMER_NOT_FOUND, id));
		Address address=addressRepository.findById(customer.getAddress().getAddressId())
				.orElseThrow(() -> new ServiceException(ErrorCode.ADDRESS_NOT_FOUND, customer.getAddress().getAddressId()));
		address.setLine1(customer.getAddress().getLine1());
		address.setLine2(customer.getAddress().getLine2());
		address.setLine3(customer.getAddress().getLine3());
		address.setCity(customer.getAddress().getCity());
		address.setState(customer.getAddress().getState());
		address.setZipCode(customer.getAddress().getZipCode());
		updateCustomer.setFirstName(customer.getFirstName());
		updateCustomer.setLastName(customer.getLastName());
		updateCustomer.setAddress(customer.getAddress());
		updateCustomer.setGovtId(customer.getGovtId());
		updateCustomer.setDateOfBirth(customer.getDateOfBirth());
		updateCustomer.setLicenceId(customer.getLicenceId());
		updateCustomer.setContactNumber(customer.getContactNumber());
		updateCustomer.setAccountStatus(customer.isAccountStatus());
		addressRepository.save(address);
		customerRepository.save(updateCustomer);
	}

	@Override
	public void deleteCustomer(Long id) {
		log.info(">> deleteCustomer({})", id);
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ServiceException(ErrorCode.CUSTOMER_NOT_FOUND, id));
		customerRepository.delete(customer);
	}

}

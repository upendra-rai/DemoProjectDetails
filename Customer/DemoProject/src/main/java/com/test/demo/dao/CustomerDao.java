package com.test.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demo.entities.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}

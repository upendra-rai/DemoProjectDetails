package com.test.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demo.entities.Customer;
import com.test.demo.entities.CustomerCategory;

public interface CustomerCategoryDao extends JpaRepository<CustomerCategory, Long> {

}

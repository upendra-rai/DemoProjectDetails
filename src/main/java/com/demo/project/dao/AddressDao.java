package com.demo.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.models.Company;
import com.demo.project.models.one.to.one.uni.dir.Address;
import com.demo.project.one.to.many.bi.dir.models.Color;
@Repository
public interface AddressDao extends JpaRepository<Address, Long> {

}

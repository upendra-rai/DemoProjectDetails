package com.demo.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.models.Company;
@Repository
public interface CompanyDao extends JpaRepository<Company, Long> {

}

package com.demo.project.services;

import java.util.List;

import com.demo.project.models.Company;

public interface CompanyService {
	
	public List<Company>getCompanies();
	public void saveCompany(Company company);
	public Company getById(Long id);
	public void updateCompany(Long id,Company company);
	public void deleteCompany(Long id);
}

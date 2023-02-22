package com.demo.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.project.dao.CompanyDao;
import com.demo.project.models.Company;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companyDao;

	@Override
	public List<Company> getCompanies() {
		return companyDao.findAll();
	}

	@Override
	@Transactional
	public void saveCompany(Company company) {
		companyDao.save(company);

	}

	@Override
	public Company getById(Long id) {
		Company company = companyDao.findById(id).orElseThrow(() -> new RuntimeException("Customer Id not Found"));
		return company;
	}

	@Override
	@Transactional
	public void updateCompany(Long id, Company company) {
		Company savecompany = companyDao.findById(id).orElseThrow(() -> new RuntimeException("Customer Id mot found"));
		savecompany.setCompanyName(company.getCompanyName());
		savecompany.setCompanyLocation(company.getCompanyLocation());
		savecompany.setDescripation(company.getDescripation());
		companyDao.save(savecompany);
	}

	@Override
	@Transactional
	public void deleteCompany(Long id) {
		Company company = companyDao.findById(id).orElseThrow(() -> new RuntimeException("Customer Id mot found"));
		companyDao.delete(company);
	}


}

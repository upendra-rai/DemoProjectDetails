package com.demo.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.models.Company;
import com.demo.project.services.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	@GetMapping
	public ResponseEntity<List<Company>> getCompanies() {
		return ResponseEntity.ok(companyService.getCompanies());
	}

	@PostMapping
	public ResponseEntity<Void> saveCompany(@RequestBody Company company) {
		companyService.saveCompany(company);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Company> getById(@PathVariable Long id) {
		return ResponseEntity.ok(companyService.getById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCompany(@PathVariable Long id, @RequestBody Company company) {
		companyService.updateCompany(id, company);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
		companyService.deleteCompany(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}

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
import com.demo.project.one.to.many.bi.dir.models.Color;
import com.demo.project.one.to.many.bi.dir.models.Laptop;
import com.demo.project.services.CompanyService;
import com.demo.project.services.LaptopService;

@RestController
@RequestMapping("/laptop")
public class LaptopController {

	@Autowired
	private LaptopService laptopService;

	@GetMapping
	public ResponseEntity<List<Laptop>> getLists() {
		return ResponseEntity.ok(laptopService.getLists());
	}
	
	@GetMapping("/colorlist")
	public ResponseEntity<List<Color>> getColorList() {
		return ResponseEntity.ok(laptopService.getColorList());
	}

	@PostMapping
	public ResponseEntity<Void> saveCompany(@RequestBody Laptop laptop) {
		laptopService.saveData(laptop);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Laptop> getById(@PathVariable Long id) {
		return ResponseEntity.ok(laptopService.getById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCompany(@PathVariable Long id, @RequestBody Laptop laptop) {
		laptopService.updateData(id, laptop);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
		laptopService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}

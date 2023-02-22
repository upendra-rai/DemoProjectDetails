package com.demo.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import com.demo.project.models.PagedResponseDto;
import com.demo.project.models.Student;
import com.demo.project.models.one.to.one.uni.dir.User;
import com.demo.project.one.to.many.bi.dir.models.Color;
import com.demo.project.one.to.many.bi.dir.models.Laptop;
import com.demo.project.services.CompanyService;
import com.demo.project.services.LaptopService;
import com.demo.project.services.StudentService;
import com.demo.project.services.UserService;

@RestController
@RequestMapping("/student")
public class StudentPageableController {

	@Autowired
	private StudentService studentService;

	@GetMapping
	public ResponseEntity<PagedResponseDto<Student>> getLists(Pageable pageable) {
		return ResponseEntity.ok(studentService.getLists(pageable));
	}

	@PostMapping
	public ResponseEntity<Void> saveCompany(@RequestBody Student student) {
		studentService.saveData(student);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		studentService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}

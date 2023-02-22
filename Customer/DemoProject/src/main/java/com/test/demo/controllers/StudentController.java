package com.test.demo.controllers;

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

import com.test.demo.entities.Student;
import com.test.demo.services.StudentService;

@RestController
@RequestMapping("v1/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	// Get All Student List
	@GetMapping
	public ResponseEntity<List<Student>> getCustomers() {
		return ResponseEntity.ok(studentService.getStudents());
	}

	// Save New Student Details
	@PostMapping
	public ResponseEntity<Void> createStudent(@RequestBody Student student) {
		studentService.createStudent(student);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	// Get Student Details By Id
	@GetMapping("/{id}")
	public ResponseEntity<Student> getByStudentId(@PathVariable long id) {
		return ResponseEntity.ok(studentService.getByStudentId(id));
	}

	// Update Student By Id
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateStudent(@PathVariable long id, @RequestBody Student student) {
		studentService.updateStudent(id, student);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// Delete Student By Id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
		studentService.deleteStudent(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}

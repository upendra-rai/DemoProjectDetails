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

import com.demo.project.models.Book;
import com.demo.project.models.Company;
import com.demo.project.models.one.to.one.uni.dir.User;
import com.demo.project.one.to.many.bi.dir.models.Color;
import com.demo.project.one.to.many.bi.dir.models.Laptop;
import com.demo.project.services.BookService;
import com.demo.project.services.CompanyService;
import com.demo.project.services.LaptopService;
import com.demo.project.services.UserService;

@RestController
@RequestMapping("/book")
public class BulkBookController {

	@Autowired
	private BookService bookService;

	@GetMapping
	public ResponseEntity<List<Book>> getLists() {
		return ResponseEntity.ok(bookService.getLists());
	}

	@PostMapping("/forloop")
	public ResponseEntity<Book> saveCompany(@RequestBody Book book) {
		return ResponseEntity.ok(bookService.saveData(book));
	}
	
	@PostMapping("/batch/data")
	public ResponseEntity<Book> saveBatchData( ) {
		return ResponseEntity.ok(bookService.saveBatchData());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
		bookService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}

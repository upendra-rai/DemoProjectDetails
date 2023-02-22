package com.demo.project.services;

import java.util.List;

import com.demo.project.models.Book;
import com.demo.project.models.Company;
import com.demo.project.models.one.to.one.uni.dir.User;
import com.demo.project.one.to.many.bi.dir.models.Color;
import com.demo.project.one.to.many.bi.dir.models.Laptop;

public interface BookService {
	
	public List<Book>getLists();
	
	public Book saveData(Book book);
	
	public Book updateData(Long id,Book book);
	
	public void deleteById(Long id);

	public Book saveBatchData();
}

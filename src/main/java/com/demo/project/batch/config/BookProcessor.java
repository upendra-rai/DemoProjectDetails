package com.demo.project.batch.config;

import org.springframework.batch.item.ItemProcessor;

import com.demo.project.models.Book;

public class BookProcessor implements ItemProcessor<Book, Book>{

	@Override
	public Book process(Book item) throws Exception {
		return item;
	}

}

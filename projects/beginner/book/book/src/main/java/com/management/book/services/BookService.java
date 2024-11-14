package com.management.book.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.book.models.Book;
import com.management.book.repositories.BookRepository;
import com.management.book.services.exceptions.ResourceNotFoundException;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;
	
	public List<Book> findAll() {
		return repository.findAll();
	}
	
	public Book findById(Long id) {
		Optional<Book> entity = repository.findById(id);
		
		return entity.orElseThrow(() -> new ResourceNotFoundException(id));
	}
}

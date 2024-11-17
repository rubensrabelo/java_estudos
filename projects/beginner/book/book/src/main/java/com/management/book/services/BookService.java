package com.management.book.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.management.book.models.Author;
import com.management.book.models.Book;
import com.management.book.models.Category;
import com.management.book.repositories.AuthorRepository;
import com.management.book.repositories.BookRepository;
import com.management.book.repositories.CategoryRepository;
import com.management.book.services.exceptions.DatabaseException;
import com.management.book.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
	
	public Book findById(Long id) {
		Optional<Book> entity = bookRepository.findById(id);
		
		return entity.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Book create(Book obj) {
		Author author = validAuthor(obj.getAuthor().getId());
		Set<Category> categories = validCategories(obj);
		
		obj.setAuthor(author);
		
		obj.getCategories().clear();
		obj.addCategories(categories);
		
		return bookRepository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			bookRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Book update(Long id, Book obj) {
		try {
			Author author = validAuthor(obj.getAuthor().getId());
			Set<Category> categories = validCategories(obj);
			
			Book entity = bookRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException(id));
			
			updateData(entity, obj, author, categories);
			
			return bookRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private Author validAuthor(Long id) {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		return author;
	}
	
	private Set<Category> validCategories(Book obj) {
		Set<Category> categories = obj.getCategories().stream()
				 .map(cat -> categoryRepository.findById(cat.getId())
						 .orElseThrow(() -> new ResourceNotFoundException(cat.getId())))
				 .collect(Collectors.toSet());
		return categories;
	}

	private void updateData(Book entity, Book obj, Author author, Set<Category> categories) {
		entity.setTitle(obj.getTitle());
        entity.setIsbn(obj.getIsbn());
        entity.setPublicationDate(obj.getPublicationDate());
        entity.setAuthor(author);
        
        if(!entity.getAuthor().equals(author))
        	entity.setAuthor(author);
        
        entity.getCategories().clear();
        entity.addCategories(categories);
        categories.forEach(cat -> cat.getBooks().add(entity));
	}
}

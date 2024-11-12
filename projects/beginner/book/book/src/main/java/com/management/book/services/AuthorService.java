package com.management.book.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.management.book.models.Author;
import com.management.book.repositories.AuthorRepository;
import com.management.book.services.exceptions.DatabaseException;
import com.management.book.services.exceptions.DuplicateResourceException;
import com.management.book.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository repository;
	
	public List<Author> findAll() {
		return repository.findAll();
	}
	
	public Author findByID(Long id) {
		Optional<Author> entity = repository.findById(id);
		
		return entity.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Author create(Author obj) {
		repository.findAuthorByName(obj.getName()).ifPresent(existingAuthor -> {
			new DuplicateResourceException("Author with name " + obj.getName() + " already exists.");
		});
		
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Author update(Long id, Author obj) {
		try {
			Author entity = repository.findById(id).get();
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Author entity, Author obj) {
		entity.setName(obj.getName());
		entity.setBiography(obj.getBiography());
		entity.setNationality(obj.getNationality());
	}
}

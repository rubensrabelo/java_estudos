package com.productManagement.productManagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.productManagement.productManagement.models.Category;
import com.productManagement.productManagement.repositories.CategoryRepository;
import com.productManagement.productManagement.services.exceptions.DatabaseException;
import com.productManagement.productManagement.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	public CategoryRepository repository;
	
	public List<Category> findAll() {
		List<Category> catogories = repository.findAll();
		
		return catogories;
	}
	
	public Category findById(Long id) {
		Optional<Category> category = repository.findById(id);
		
		return category.get();
	}
	
	public Category insert(Category category) {
		return repository.save(category);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Category update(Long id, Category category) {
		try {
			Category entity = repository.findById(id).get();
			updatedData(entity, category);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updatedData(Category entity, Category category) {
		entity.setName(category.getName());
		entity.setDescription(category.getDescription());
	}
}

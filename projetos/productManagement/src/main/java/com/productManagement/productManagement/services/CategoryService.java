package com.productManagement.productManagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productManagement.productManagement.models.Category;
import com.productManagement.productManagement.repositories.CategoryRepository;

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
}

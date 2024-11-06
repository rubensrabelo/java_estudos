package com.project.product.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.product.models.Category;
import com.project.product.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll() {
		List<Category> list = repository.findAll();
		
		return list;
	}
	
	public Category findById(Long id) {
		Optional<Category> entity = repository.findById(id);
		
		return entity.get();
	}
}

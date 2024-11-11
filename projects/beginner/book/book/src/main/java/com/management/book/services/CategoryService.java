package com.management.book.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.book.models.Category;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryService repository;
	
	public List<Category> findAll() {
		return repository.findAll();
	}
}

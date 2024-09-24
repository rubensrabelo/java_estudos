package com.productManagement.productManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.productManagement.productManagement.models.Product;
import com.productManagement.productManagement.repositories.ProductRepository;
import com.productManagement.productManagement.service.exceptions.DatabaseException;
import com.productManagement.productManagement.service.exceptions.ResourceNotFoundException;

public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll() {
		List<Product> products = repository.findAll();
		
		return products;
	}
	
	public Product findById(Long id) {
		Optional<Product> product = repository.findById(id);
		
		return product.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Product insert(Product product) {
		return repository.save(product);
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
}

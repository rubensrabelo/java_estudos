package com.productManagement.productManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.productManagement.productManagement.models.Product;
import com.productManagement.productManagement.repositories.ProductRepository;
import com.productManagement.productManagement.service.exceptions.DatabaseException;
import com.productManagement.productManagement.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
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
	
	public List<Product> findByName(String name) {
		return repository.findByNameContainingIgnoreCase(name);
	}
	
	public List<Product> findByCategory(String category) {
		return repository.findByCategory(category);
	}
	
	public List<Product> findByNameAndCategory(String name, String Category) {
		return repository.findByNameContainingIgnoreCaseAndCategory(name, Category);
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
	
	public Product update(Long id, Product product) {
		try {
			
			Product updateProduct = repository.getReferenceById(id);
			updateData(updateProduct, product);
			
			return repository.save(updateProduct);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Product updateProduct, Product product) {
		updateProduct.setName(product.getName());
		updateProduct.setCategory(product.getCategory());
		updateProduct.setPrice(product.getPrice());
		updateProduct.setQuantity(product.getQuantity());
	}
}

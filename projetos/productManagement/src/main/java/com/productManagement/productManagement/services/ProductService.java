package com.productManagement.productManagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.productManagement.productManagement.models.Product;
import com.productManagement.productManagement.repositories.ProductRepository;
import com.productManagement.productManagement.services.exceptions.DatabaseException;
import com.productManagement.productManagement.services.exceptions.ResourceNotFoundException;

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
		
		return product.get();
	}
	
	public Product save(Product product) {
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
			Product entity = repository.findById(id).get();
			updatedData(entity, product);
			return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updatedData(Product entity, Product product) {
		entity.setName(product.getName());
		entity.setDescription(product.getDescription());
		entity.setQuantity(product.getQuantity());
		entity.setUnitPrice(product.getUnitPrice());
	}
}

package com.productManagement.productManagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productManagement.productManagement.models.Product;
import com.productManagement.productManagement.repositories.ProductRepository;

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
}

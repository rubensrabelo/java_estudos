package com.productManagement.productManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productManagement.productManagement.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByNameContainingIgnoreCase(String name);
	
	List<Product> findByCategory(String caategory);
	
	List<Product> findByNameContainingIgnoreCaseAndCategory(String name, String category);
}

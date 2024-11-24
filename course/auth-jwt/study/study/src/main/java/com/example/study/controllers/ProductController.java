package com.example.study.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.domain.product.Product;
import com.example.study.domain.product.ProductRequestDTO;
import com.example.study.domain.product.ProductResponseDTO;
import com.example.study.repositories.ProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("products")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	
	@PostMapping
	public ResponseEntity<ProductResponseDTO> postProduct(@RequestBody @Valid ProductRequestDTO body) {
		Product newProduct = new Product(body);
		newProduct = this.repository.save(newProduct);
		
		ProductResponseDTO response = new ProductResponseDTO(newProduct);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
		List<ProductResponseDTO> productList = this.repository.findAll().stream()
				.map(ProductResponseDTO::new).toList();
		
		return ResponseEntity.ok(productList);
	}
}

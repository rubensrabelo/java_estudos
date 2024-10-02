package com.productManagement.productManagement.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.productManagement.productManagement.models.Product;
import com.productManagement.productManagement.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/products")
@Tag(name = "Products", description = "Operations related to product management")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@Operation(summary = "Retrieve all products", description = "Fetch a list of all available products")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved the list of products"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = service.findAll();
		
		return ResponseEntity.ok().body(products);
	}
	
	@Operation(summary = "Retrieve a product by ID", description = "Fetch a product by its unique identifier")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the product"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findByID(@PathVariable Long id) {
		Product product = service.findById(id);
		
		return ResponseEntity.ok().body(product);
	}
	
	@Operation(summary = "Search products", description = "Search for products by name and/or category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the search results"),
        @ApiResponse(responseCode = "400", description = "Invalid search parameters")
    })
	@GetMapping(value = "/search")
	public ResponseEntity<List<Product>> findByName(
				@RequestParam(value = "name", required = false) String name,
				@RequestParam(value = "category", required = false) String category
			) {
		List<Product> products = service.search(name, category);
		
		return ResponseEntity.ok().body(products);
	}
	
	@Operation(summary = "Create a new product", description = "Insert a new product into the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Product successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
	@PostMapping
	public ResponseEntity<Product> insert(@Valid @RequestBody Product product) {
		product = service.insert(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(product.getId()).toUri();
		
		return ResponseEntity.created(uri).body(product);
	}
	
	@Operation(summary = "Delete a product", description = "Remove a product from the system by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Product successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	 @Operation(summary = "Update a product", description = "Update the details of an existing product")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Product successfully updated"),
	        @ApiResponse(responseCode = "400", description = "Invalid input"),
	        @ApiResponse(responseCode = "404", description = "Product not found")
	    })
	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product){
		product = service.update(id, product);
		
		return ResponseEntity.ok().body(product);
	}
}

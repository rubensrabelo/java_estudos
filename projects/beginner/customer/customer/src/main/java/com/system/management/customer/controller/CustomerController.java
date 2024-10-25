package com.system.management.customer.controller;

import static com.system.management.customer.util.MediaType.APPLICATION_JSON;
import static com.system.management.customer.util.MediaType.APPLICATION_XML;
import static com.system.management.customer.util.MediaType.APPLICATION_YAML;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.system.management.customer.data.vo.CustomerVO;
import com.system.management.customer.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/customers/v1")
@Tag(name = "Customer", description = "Endpoints for Managing Customer")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping(produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML})
	@Operation(summary = "Find all customers", description = "Finds all customers", tags = { "Customer" }, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CustomerVO.class))) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<List<CustomerVO>> findAll() {
		List<CustomerVO> customers = service.findAll();
		
		return ResponseEntity.ok().body(customers);
	}
	
	@GetMapping(value = "/{id}", produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML})
	@Operation(summary = "Finds a customer", description = "Finds a customer", tags = { "Customer" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = CustomerVO.class))),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<CustomerVO> findById(@PathVariable("id") Long id) {
		CustomerVO customer = service.findById(id);
		
		return ResponseEntity.ok().body(customer);
	}
	
	@PostMapping(consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML}, produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML})
	@Operation(summary = "Adds a new Customer", description = "Adds a new Customer by passing in a JSON, XML or YML representation of the task!", tags = {
	"Customer" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = CustomerVO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<CustomerVO> create(@RequestBody CustomerVO customer) {
		customer = service.create(customer);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/{id}").buildAndExpand(customer.getKey()).toUri();
		
		return ResponseEntity.created(uri).body(customer);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletes a Customer", description = "Deletes a Customer by passing in a JSON, XML or YML representation of the task!", tags = {
	"Customer" }, responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}", consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML}, produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML})
	@Operation(summary = "Updates a Customer", description = "Updates a Customer by passing in a JSON, XML or YML representation of the task!", tags = {
	"Customer" }, responses = {
			@ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = CustomerVO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<CustomerVO> update(@PathVariable("id") Long id, @RequestBody CustomerVO customer) {
		customer = service.update(id, customer);
		
		return ResponseEntity.ok().body(customer);
	}
}

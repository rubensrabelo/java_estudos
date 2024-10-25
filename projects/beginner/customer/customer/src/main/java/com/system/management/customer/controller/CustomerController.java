package com.system.management.customer.controller;

import java.net.URI;
import java.util.List;

import static com.system.management.customer.util.MediaType.*;

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

@RestController
@RequestMapping("/api/customers/v1")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping(produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML})
	public ResponseEntity<List<CustomerVO>> findAll() {
		List<CustomerVO> customers = service.findAll();
		
		return ResponseEntity.ok().body(customers);
	}
	
	@GetMapping(value = "/{id}", produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML})
	public ResponseEntity<CustomerVO> findById(@PathVariable("id") Long id) {
		CustomerVO customer = service.findById(id);
		
		return ResponseEntity.ok().body(customer);
	}
	
	@PostMapping(consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML}, produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML})
	public ResponseEntity<CustomerVO> create(@RequestBody CustomerVO customer) {
		customer = service.create(customer);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/{id}").buildAndExpand(customer.getKey()).toUri();
		
		return ResponseEntity.created(uri).body(customer);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}", consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML}, produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML})
	public ResponseEntity<CustomerVO> update(@PathVariable("id") Long id, @RequestBody CustomerVO customer) {
		customer = service.update(id, customer);
		
		return ResponseEntity.ok().body(customer);
	}
}

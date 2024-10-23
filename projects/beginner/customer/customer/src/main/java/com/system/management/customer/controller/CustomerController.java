package com.system.management.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.management.customer.data.vo.CustomerVO;
import com.system.management.customer.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping
	public ResponseEntity<List<CustomerVO>> findAll() {
		List<CustomerVO> customers = service.findAll();
		
		return ResponseEntity.ok().body(customers);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CustomerVO> findById(@PathVariable("id") Long id) {
		CustomerVO customer = service.findById(id);
		
		return ResponseEntity.ok().body(customer);
	}
}

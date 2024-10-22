package com.course.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.course.data.vo.v1.security.AccountCredentialsVO;
import com.course.course.services.AuthService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Endpoint")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/signin")
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		if(checkIfParamnsIsNotNull(data)) 
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		
		var token = authService.signin(data);
		
		if(token == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		
		return token;
	}

	private boolean checkIfParamnsIsNotNull(AccountCredentialsVO data) {
		return data == null || data.getUserName() == null || data.getUserName().isBlank() || data.getPassword() == null;
	}
}

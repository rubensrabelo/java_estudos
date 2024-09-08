package com.registerUsers.registerUsers.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registerUsers.registerUsers.models.User;
import com.registerUsers.registerUsers.services.UserService;

import jakarta.annotation.Resource;

@Resource
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	public ResponseEntity<List<User>> findAll() {
		List<User> users = service.findAll();
		
		return ResponseEntity.ok().body(users);
	}
}

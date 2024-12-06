package com.project.todolist.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.todolist.dto.LoginRequestDTO;
import com.project.todolist.dto.RegisterRequestDTO;
import com.project.todolist.dto.ResponseDTO;
import com.project.todolist.infra.security.TokenService;
import com.project.todolist.models.User;
import com.project.todolist.repositories.UserRepository;

@RestController
@RequestMapping(value = "auth")
public class AuthController {
	
	private final UserRepository repository;
	private final PasswordEncoder passwordEnconder;
	private final TokenService tokenService;
	
	public AuthController(UserRepository repository, PasswordEncoder passwordEnconder, TokenService tokenService) {
		this.repository = repository;
		this.passwordEnconder = passwordEnconder;
		this.tokenService = tokenService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO dto) {
		User user = this.repository.findByEmail(dto.email())
						.orElseThrow(() -> new RuntimeException("User not found"));
		
		
		if(passwordEnconder.matches(dto.password(), user.getPassword())) {
			String token = this.tokenService.generateToken(user);
			ResponseDTO response = new ResponseDTO(user.getName(), token);
			return ResponseEntity.ok().body(response);
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO dto) {
		Optional<User> user = this.repository.findByEmail(dto.email());
		
		if(user.isEmpty()) {
			User newUser = new User();
			newUser.setName(dto.name());
			newUser.setEmail(dto.email());
			newUser.setPassword(passwordEnconder.encode(dto.password()));
			
			this.repository.save(newUser);
			
			String token = this.tokenService.generateToken(newUser);
			
			ResponseDTO response = new ResponseDTO(newUser.getName(), token);
			
			return ResponseEntity.ok().body(response);
		}
		
		return ResponseEntity.badRequest().build();
	}
}

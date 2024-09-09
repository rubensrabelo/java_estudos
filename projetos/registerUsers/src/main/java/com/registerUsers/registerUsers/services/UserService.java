package com.registerUsers.registerUsers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.registerUsers.registerUsers.models.User;
import com.registerUsers.registerUsers.repositories.UserRepository;
import com.registerUsers.registerUsers.services.exceptions.DatabaseException;
import com.registerUsers.registerUsers.services.exceptions.EmailAlreadyExistsException;
import com.registerUsers.registerUsers.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> userOpt = repository.findById(id);
		
		return userOpt.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User user) {
		Optional<User> existingUser = repository.findByEmail(user.getEmail());
		
		if(existingUser.isPresent())
			throw new EmailAlreadyExistsException(user.getEmail());
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return repository.save(user);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User user) {
		try {
			Optional<User> entity = repository.findById(id);
			updateData(entity.get(), user);
			
			return repository.save(entity.get());
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User user) {
		
		entity.setName(user.getName());
		entity.setBirthDate(user.getBirthDate());
		entity.setGender(user.getGender());
	}
}

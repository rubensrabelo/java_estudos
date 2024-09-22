package com.userRegister.userRegister.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	public PasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public User create(User user) {
		User existUser = repository.findByUsername(user.getUsername());
		
		if(existUser != null) throw new Error("User already exists!");
		
		user.setPassword(passwordEnconder().encode(user.getPassword()));
		
		User createdUser = repository.save(user);
		
		return createdUser;
	}

}

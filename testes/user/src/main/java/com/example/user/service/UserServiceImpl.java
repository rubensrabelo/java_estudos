package com.example.user.service;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(
            String firstName,
            String lastName,
            String email,
            String password,
            String confirmPassword
    ) {
        if(firstName.isEmpty() || firstName.trim().isEmpty())
            throw new IllegalArgumentException("User's first name is empty.");

        if(lastName.isEmpty() || lastName.trim().isEmpty())
            throw new IllegalArgumentException("User's last name is empty.");

        User user = new User(
                UUID.randomUUID().toString(),
                firstName,
                lastName,
                email,
                password,
                confirmPassword
        );

        boolean isUserCreated = userRepository.save(user);

        if(!isUserCreated) throw new UserServiceException("Could not create user.");

        return user;
    }
}

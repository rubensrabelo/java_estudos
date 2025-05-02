package com.example.user.service;

import com.example.user.model.User;

public class UserServiceImpl implements UserService {

    @Override
    public User createUser(
            Long id,
            String firstName,
            String lastName,
            String email,
            String password,
            String confirmPassword
    ) {
        if(firstName.isEmpty() || firstName.trim().isEmpty())
            throw new IllegalArgumentException("User's first name is empty.");

        return new User(
                id,
                firstName,
                lastName,
                email,
                password,
                confirmPassword
        );
    }
}

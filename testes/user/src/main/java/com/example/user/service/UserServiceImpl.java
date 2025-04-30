package com.example.user.service;

import com.example.user.model.User;

public class UserServiceImpl implements UserService {

    @Override
    public User createUser(
            String firstName,
            String lastName,
            String email,
            String password,
            String confirmPassword
    ) {
        return new User(
                firstName,
                lastName,
                email,
                password,
                confirmPassword
        );
    }
}

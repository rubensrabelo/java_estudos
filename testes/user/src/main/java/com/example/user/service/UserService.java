package com.example.user.service;

import com.example.user.model.User;

public interface UserService {

    User createUser(
            Long id,
            String firstName,
            String lastName,
            String email,
            String password,
            String confirmPassword
    );
}

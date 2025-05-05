package com.example.user.repository;

import com.example.user.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {

    Map<String, User> users = new HashMap<>();

    @Override
    public boolean save(User user) {
        boolean isUserCreated = false;

        if(!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            isUserCreated = true;
        }

        return isUserCreated;
    }
}


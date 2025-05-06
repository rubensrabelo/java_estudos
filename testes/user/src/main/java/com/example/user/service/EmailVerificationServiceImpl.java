package com.example.user.service;

import com.example.user.model.User;

public class EmailVerificationServiceImpl implements EmailVerificationService {
    @Override
    public void scheduleEmailConfirmation(User user) {
        System.out.println("Email confirmation scheduled");
    }
}

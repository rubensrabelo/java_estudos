package com.example.user.service;

import com.example.user.model.User;

public interface EmailVerificationService {
    void scheduleEmailConfirmation(User user);
}

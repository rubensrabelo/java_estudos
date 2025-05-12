package com.appsdeveloperblog.tutorials.junit.ui.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(
        controllers = UsersController.class,
        excludeAutoConfiguration = {SecurityAutoConfiguration.class}
)
public class UserControllerWebLayerTest {

    @DisplayName("User can be created")
    @Test
    void testCreateUser_whenValidUserDetailsProvided_returnsCreatedUserDetails() {}
}

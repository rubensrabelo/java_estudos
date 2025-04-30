package com.example.user.service;

import com.example.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

	@DisplayName("User object created")
	@Test
	void testCreateUser_whenUserDetailsProvided_returnUserObject() {
		// Arrange
		UserService userService = new UserServiceImpl();
		String firstName = "John";
		String lastName = "Doe";
		String email = "john.doe@example.com";
		String password = "password";
		String confirmPassword = "password";

		// Act
		User user = userService.createUser(
				firstName,
				lastName,
				email,
				password,
				confirmPassword
		);

		// Assert
		assertNotNull(user, "The createUser() should not have returned null.");
		assertEquals(firstName, user.getFirstName(), "User's first name is not the same.");
		assertEquals(lastName, user.getLastName(), "User's last name is not the same.");
		assertEquals(email, user.getEmail(), "User's email is not the same.");
	}
}
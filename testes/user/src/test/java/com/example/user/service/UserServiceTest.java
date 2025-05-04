package com.example.user.service;

import com.example.user.model.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

	UserService userService;
	Long id;
	String firstName;
	String lastName;
	String email;
	String password;
	String confirmPassword;

	@BeforeEach
	void init() {
		userService = new UserServiceImpl();
		id = 1L;
		firstName = "John";
		lastName = "Doe";
		email = "john.doe@example.com";
		password = "password";
		confirmPassword = "password";
	}

	@DisplayName("User object created")
	@Test
	void testCreateUser_whenUserDetailsProvided_returnUserObject() {
		// Act
		User user = userService.createUser(
				id,
				firstName,
				lastName,
				email,
				password,
				confirmPassword
		);

		// Assert
		assertNotNull(user.getId(), "User's id should not be null.");

		assertNotNull(user, "The createUser() should not have returned null.");
		assertEquals(firstName, user.getFirstName(), "User's first name is not the same.");
		assertEquals(lastName, user.getLastName(), "User's last name is not the same.");
		assertEquals(email, user.getEmail(), "User's email is not the same.");
	}

	@DisplayName("Empty first name causes correct exception")
	@Test
	void testCreateUser_whenUserFirstNameIsEmpty_returnIllegalArgumentException() {
		// Arrange
		firstName = "";
		String expectedExceptionMessage = "User's first name is empty.";

		// Act & Assert
		 IllegalArgumentException message = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			User user = userService.createUser(
					id,
					firstName,
					lastName,
					email,
					password,
					confirmPassword
			);
		}, "Empty first name causes correct exception");

		 assertEquals(expectedExceptionMessage, message.getMessage(), "Exception message is not correct.");

	}

	@DisplayName("Empty last name causes correct exception")
	@Test
	void testCreateUser_whenUserLastNameIsEmpty_returnIllegalArgumentException() {
		lastName = "";
		String expectedExceptionMessage = "User's last name is empty.";

		IllegalArgumentException message = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			User user = userService.createUser(
					id,
					firstName,
					lastName,
					email,
					password,
					confirmPassword
			);
		}, "Empty last name causes correct exception");

		assertEquals(expectedExceptionMessage, message.getMessage(), "Exception message is not correct.");
	}
}
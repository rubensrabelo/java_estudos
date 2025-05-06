package com.example.user.service;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	String firstName;
	String lastName;
	String email;
	String password;
	String confirmPassword;

	@BeforeEach
	void init() {
		firstName = "John";
		lastName = "Doe";
		email = "john.doe@example.com";
		password = "password";
		confirmPassword = "password";
	}

	@DisplayName("User object created")
	@Test
	void testCreateUser_whenUserDetailsProvided_returnUserObject() {
		// Arrange
		Mockito.when(userRepository.save(any(User.class))).thenReturn(true);

		// Act
		User user = userService.createUser(
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

		verify(userRepository, times(1))
				.save(any(User.class));
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
					firstName,
					lastName,
					email,
					password,
					confirmPassword
			);
		}, "Empty last name causes correct exception");

		assertEquals(expectedExceptionMessage, message.getMessage(), "Exception message is not correct.");
	}

	@DisplayName("If save() method causes RuntimeException, a UserServiceException is thrown")
	@Test
	void testCreateUser_whenSaveMethodThrowsException_thenThrowsUserServiceException() {
		// Arrange
		when(userRepository.save(any(User.class))).thenThrow(RuntimeException.class);

		// Act & Assert
		assertThrows(UserServiceException.class, () -> {
			userService.createUser(
					firstName,
					lastName,
					email,
					password,
					confirmPassword
			);
		}, "Should have thrown UserServiceException instead.");
	}
}
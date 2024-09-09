package com.registerUsers.registerUsers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.registerUsers.registerUsers.services.exceptions.EmailAlreadyExistsException;

@RestControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<StandardError> handleEmailAlreadyExists(EmailAlreadyExistsException e, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(
				Instant.now(), 
				status.value(), 
				"Email already exists", 
				e.getMessage(), 
				request.getDescription(false));
		
		return ResponseEntity.status(status).body(err);
	}
}

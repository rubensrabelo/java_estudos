package com.productManagement.productManagement.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource with id: " + id + " not found.");
	}
}

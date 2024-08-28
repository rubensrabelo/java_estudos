package com.course.project.firstProject.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Instant timestamp;
	private String message;
	private String details;
	
	public ExceptionResponse(Instant timestamp, String message, String details) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}

package com.course.course.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class ExceptionResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Instant timestamp;
	private String message;
	private String detail;
	
	public ExceptionResponse(Instant timestamp, String message, String detail) {
		this.timestamp = timestamp;
		this.message = message;
		this.detail = detail;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}

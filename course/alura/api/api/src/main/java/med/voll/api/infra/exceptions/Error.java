package med.voll.api.infra.exceptions;

import org.springframework.validation.FieldError;

public record Error(String fied, String message) {
	
	public Error(FieldError error) {
		this(error.getField(), error.getDefaultMessage());
	}
}

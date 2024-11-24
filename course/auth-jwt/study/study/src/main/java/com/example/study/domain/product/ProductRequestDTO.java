package com.example.study.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequestDTO {
	
	@NotBlank
	String name;
	
	@NotNull
	Integer price;
}

package com.course.project.firstProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Restful API with Spring Boot 3")
						.version("v1")
						.description("")
						.termsOfService("")
						.license(new License().name("apache 2.0")
								.url("")));
	}
}

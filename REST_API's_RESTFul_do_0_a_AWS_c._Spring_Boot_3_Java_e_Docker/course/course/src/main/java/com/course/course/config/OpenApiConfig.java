package com.course.course.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
	
	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("User Management API")
						.version("v1")
						.description("API to manage user")
						.contact(new Contact()
								.name("Rubens Rabelo")
								.email("rubensrabelo@alu.ufc.br"))
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.org")))
				.servers(Arrays.asList(new Server().url("http://localhost:8080")))
				.addTagsItem(new io.swagger.v3.oas.models.tags.Tag()
						.name("Products")
						.description("Operations related to user"))
				.addSecurityItem(new SecurityRequirement());
	}
}

package com.productManagement.productManagement.config;

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
						.title("Product Management API")
						.version("1.0")
						.description("API to manage products")
						.contact(new Contact()
								.name("Rubens Rabelo")
								.email("rubensrabelo@alu.ufc.br"))
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.org")))
				.servers(Arrays.asList(new Server().url("http://localhost:8080")))
				.addTagsItem(new io.swagger.v3.oas.models.tags.Tag()
						.name("Products")
						.description("Operations related to product management"))
				.addSecurityItem(new SecurityRequirement());
	}
}

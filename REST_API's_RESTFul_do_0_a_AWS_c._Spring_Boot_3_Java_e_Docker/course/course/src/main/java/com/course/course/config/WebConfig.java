package com.course.course.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.course.course.serialization.converter.YmlJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private static final MediaType MEDIA_TYPE_APPLICATION_YAML = MediaType.valueOf("application/x-yaml");

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		configurer.favorParameter(false)
			.ignoreAcceptHeader(false)
			.useRegisteredExtensionsOnly(false)
			.defaultContentType(org.springframework.http.MediaType.APPLICATION_JSON)
				.mediaType("json", org.springframework.http.MediaType.APPLICATION_JSON)
				.mediaType("xml", org.springframework.http.MediaType.APPLICATION_XML)
				.mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YAML);
	}
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YmlJackson2HttpMessageConverter());	
	}
	
}

package com.course.project.firstProject.serialization.converter;

import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

	protected YamlJackson2HttpMessageConverter(ObjectMapper objectMapper) {
		super(objectMapper);
		// TODO Auto-generated constructor stub
	}



}

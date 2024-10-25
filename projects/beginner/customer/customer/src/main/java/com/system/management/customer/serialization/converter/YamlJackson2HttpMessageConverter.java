package com.system.management.customer.serialization.converter;

import static com.system.management.customer.util.MediaType.APPLICATION_YAML;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

	public YamlJackson2HttpMessageConverter() {
		super(
				new YAMLMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL),
				MediaType.parseMediaType(APPLICATION_YAML)
				);
	}

}

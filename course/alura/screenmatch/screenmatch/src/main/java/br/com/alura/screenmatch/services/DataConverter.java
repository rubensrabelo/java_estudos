package br.com.alura.screenmatch.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DataConverter implements IDataConverter {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public <T> T getData(String json, Class<T> obj) {
		try {
			return mapper.readValue(json, obj);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}

package com.course.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.course.course.models.Person;
import com.course.course.services.PersonService;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@RequestMapping(value = "/{id}", 
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable(value = "id") String id) throws Exception {
		return service.findById(id);
	}
}

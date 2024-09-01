package com.course.project.firstProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.course.project.firstProject.models.Person;

@Service
public class PersonService {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public List<Person> findAll() {
		logger.info("Find all people!");
		
		List<Person> people = new ArrayList<>();
		
		return people;
	}
	
	public Person findByID(String id) {
		
		logger.info("Finding one person!");
		
		Person person = new Person();
		
		return person;
	}
}

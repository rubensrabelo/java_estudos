package com.course.course.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.course.course.models.Person;

@Service
public class PersonService {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<>();
		
		logger.info("Finding all people!");
		
		return persons;
	}
	
	public Person findById(String id) {
		
		logger.info("Finding one Person!");
		
		return new Person();
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		
		return person;
	}
	
	public Person update(Person person) {
		logger.info("Updating one person!");
		
		return person;
	}
	
	public void delete(String id) {
		logger.info("Deleting one person!");
	}
}

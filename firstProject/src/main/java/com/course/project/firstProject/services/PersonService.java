package com.course.project.firstProject.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.project.firstProject.exceptions.ResourceNotFoundException;
import com.course.project.firstProject.models.Person;
import com.course.project.firstProject.repositories.PersonRepository;

@Service
public class PersonService {
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonRepository repository;
	
	public List<Person> findAll() {
		logger.info("Find all people!");
			
		return repository.findAll();
	}
	
	public Person findByID(Long id) {
		
		logger.info("Finding one person!");
				
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));;
	}
	
	public Person create(Person person) {
		
		logger.info("Creating a person!");
		
		return repository.save(person);
	}
	
	public Person update(Person person) {
		
		logger.info("Updating a person!");
		
		Person obj = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		obj.setFirstname(person.getFirstname());
		obj.setLastname(person.getLastname());
		obj.setAddress(person.getAddress());
		obj.setGender(person.getGender());
		
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting a person!");
		
		Person person = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		repository.delete(person);
	}
}

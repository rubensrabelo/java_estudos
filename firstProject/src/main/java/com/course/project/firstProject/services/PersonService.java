package com.course.project.firstProject.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.project.firstProject.data.vo.v1.PersonVO;
import com.course.project.firstProject.exceptions.ResourceNotFoundException;
import com.course.project.firstProject.repositories.PersonRepository;

@Service
public class PersonService {
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonRepository repository;
	
	public List<PersonVO> findAll() {
		logger.info("Find all people!");
			
		return repository.findAll();
	}
	
	public PersonVO findByID(Long id) {
		
		logger.info("Finding one person!");
				
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!")); 
	}
	
	public PersonVO create(PersonVO person) {
		
		logger.info("Creating a person!");
		
		return repository.save(person);
	}
	
	public PersonVO update(PersonVO person) {
		
		logger.info("Updating a person!");
		
		PersonVO obj = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		obj.setFirstname(person.getFirstname());
		obj.setLastname(person.getLastname());
		obj.setAddress(person.getAddress());
		obj.setGender(person.getGender());
		
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting a person!");
		
		PersonVO person = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		repository.delete(person);
	}
}

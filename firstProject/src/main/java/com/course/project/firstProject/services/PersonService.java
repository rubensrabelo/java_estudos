package com.course.project.firstProject.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.project.firstProject.data.vo.v1.PersonVO;
import com.course.project.firstProject.data.vo.v2.PersonVOV2;
import com.course.project.firstProject.exceptions.ResourceNotFoundException;
import com.course.project.firstProject.mapper.DozerMapper;
import com.course.project.firstProject.models.Person;
import com.course.project.firstProject.repositories.PersonRepository;

@Service
public class PersonService {
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonRepository repository;
	
	public List<PersonVO> findAll() {
		logger.info("Find all people!");
			
		return DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
	}
	
	public PersonVO findByID(Long id) {
		
		logger.info("Finding one person!");
				
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!")); 
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		
		logger.info("Creating a person!");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		
		logger.info("Creating a person with V2!");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVOV2.class);
		
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		
		logger.info("Updating a person!");
		
		var obj = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		obj.setFirstname(person.getFirstname());
		obj.setLastname(person.getLastname());
		obj.setAddress(person.getAddress());
		obj.setGender(person.getGender());
		
		var vo = DozerMapper.parseObject(repository.save(obj), PersonVO.class);
		
		return vo;
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting a person!");
		
		var person = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		repository.delete(person);
	}
}

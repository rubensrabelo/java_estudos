package com.course.course.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.course.controllers.PersonController;
import com.course.course.data.vo.v1.PersonVO;
import com.course.course.data.vo.v2.PersonVOV2;
import com.course.course.exceptions.RequiredObjectsNullException;
import com.course.course.exceptions.ResourceNotFoundException;
import com.course.course.mapper.DozerMapper;
import com.course.course.mapper.custom.PersonMapper;
import com.course.course.models.Person;
import com.course.course.repositories.PersonRepository;

@Service
public class PersonService {
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private PersonMapper mapper;
	
	public List<PersonVO> findAll() {
		logger.info("Finding all people!");
		
		var people = DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
		
		people.stream()
			.forEach(p -> {
				try {
					p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		
		return people;
	}
	
	public PersonVO findById(Long id) throws Exception {
		
		logger.info("Finding one PersonVO!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		
		return vo;
	}
	
	public PersonVO create(PersonVO person) throws Exception {
		
		if(person == null) throw new RequiredObjectsNullException();
		
		logger.info("Creating one person!");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		
		var vo  = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}

	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating one person with v2!");
		
		var entity = mapper.convertVoToEntity(person);
		
		var vo  = mapper.convertEntityToVo(repository.save(entity));
		
		return vo;
	}
	
	public PersonVO update(PersonVO person) throws Exception {
		if(person == null) throw new RequiredObjectsNullException();
		
		logger.info("Updating one person!");
		
		var entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		repository.delete(entity);
	}
}

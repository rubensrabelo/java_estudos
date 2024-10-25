package com.system.management.customer.service;

import static  org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static  org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.system.management.customer.controller.CustomerController;
import com.system.management.customer.data.vo.CustomerVO;
import com.system.management.customer.mapper.DozerMapper;
import com.system.management.customer.model.Customer;
import com.system.management.customer.repository.CustomerRepository;
import com.system.management.customer.service.exceptions.DatabaseException;
import com.system.management.customer.service.exceptions.RequiredObjectIsNullException;
import com.system.management.customer.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	public List<CustomerVO> findAll() {
		var listVO = DozerMapper.parseListObjects(repository.findAll(), CustomerVO.class);
		
		listVO
			.stream()
			.forEach(c -> c.add(linkTo(methodOn(CustomerController.class).findById(c.getKey())).withSelfRel()));;
		
		return listVO;
	}
	
	public CustomerVO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		var vo = DozerMapper.parseObject(entity, CustomerVO.class);
		
		vo.add(linkTo(methodOn(CustomerController.class).findById(id)).withSelfRel());
		
		return vo;
	}
	
	public CustomerVO create(CustomerVO customer) {
		if(customer == null)
			throw new RequiredObjectIsNullException();
		
		var entity = DozerMapper.parseObject(customer, Customer.class);
		
		var vo = DozerMapper.parseObject(repository.save(entity), CustomerVO.class);
		
		vo.add(linkTo(methodOn(CustomerController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public CustomerVO update(Long id, CustomerVO customer) {
		if(customer == null)
			throw new RequiredObjectIsNullException();
		
		try {
			var entity = repository.findById(id).get();
			
			var customEntity = DozerMapper.parseObject(customer, Customer.class);
			
			updateData(entity, customEntity);
			
			var vo = DozerMapper.parseObject(repository.save(entity), CustomerVO.class);
			
			vo.add(linkTo(methodOn(CustomerController.class).findById(id)).withSelfRel());
			
			return vo;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Customer entity, Customer customEntity) {
		entity.setFirstName(customEntity.getFirstName());
		entity.setLastName(customEntity.getLastName());
		entity.setEmail(customEntity.getEmail());
		entity.setPhoneNumber(customEntity.getPhoneNumber());
		entity.setBirthDate(customEntity.getBirthDate());
		entity.setGender(customEntity.getGender());
	}
}

package com.project.todolist.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.todolist.controllers.TaskController;
import com.project.todolist.data.vo.v1.TaskVO;
import com.project.todolist.enums.TaskStatus;
import com.project.todolist.mapper.DozerMapper;
import com.project.todolist.models.Task;
import com.project.todolist.repositories.TaskRepository;
import com.project.todolist.services.exceptions.DatabaseException;
import com.project.todolist.services.exceptions.RequiredObjectIsNullException;
import com.project.todolist.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repository;
	
	public List<TaskVO> findAll(){
		var entities = DozerMapper.parseListObjects(repository.findAll(), TaskVO.class);
		
		entities
			.stream()
			.forEach(t -> t.add(linkTo(methodOn(TaskController.class).findById(t.getKey())).withSelfRel()));;
		
		return entities;
	}
	
	public TaskVO findById(Long id) {
		var entitie = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		var vo = DozerMapper.parseObject(entitie, TaskVO.class);
		
	    vo.add(linkTo(methodOn(TaskController.class).findById(id)).withSelfRel());
	    
	    return vo;
	}
	
	public List<TaskVO> findByTaskStatus(TaskStatus taskStatus) {
		var entities = DozerMapper.parseListObjects(
				repository.findByTaskStatus(taskStatus.getCode()), TaskVO.class);
		
		entities
			.stream()
			.forEach(t -> t.add(linkTo(methodOn(TaskController.class).findById(t.getKey())).withSelfRel()));;
		
		return entities;
	}
	
	public TaskVO insert(TaskVO taskVO) {
		
		if(taskVO == null)
			throw new RequiredObjectIsNullException();
		
		var entity = DozerMapper.parseObject(taskVO, Task.class);
		
		var vo = DozerMapper.parseObject(repository.save(entity), TaskVO.class);
		
		vo.add(linkTo(methodOn(TaskController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public TaskVO update(Long id, TaskVO taskVO) {
		if(taskVO == null) 
			throw new RequiredObjectIsNullException();
		
		try {
			Task entity = repository.findById(id).get();
			
			var taskEntity = DozerMapper.parseObject(taskVO, Task.class);
			
			updateData(entity, taskEntity);
			
			var vo = DozerMapper.parseObject(repository.save(entity), TaskVO.class);
			
			vo.add(linkTo(methodOn(TaskController.class).findById(vo.getKey())).withSelfRel());
			
			return vo;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} 
	}

	private void updateData(Task entity, Task Task) {
		entity.setName(Task.getName());
		entity.setDescription(Task.getDescription());
		entity.setTaskStatus(Task.getTaskStatus());
	}
}

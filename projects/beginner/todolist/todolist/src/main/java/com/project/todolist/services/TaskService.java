package com.project.todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.todolist.data.vo.v1.TaskVO;
import com.project.todolist.enums.TaskStatus;
import com.project.todolist.mapper.DozerMapper;
import com.project.todolist.models.Task;
import com.project.todolist.repositories.TaskRepository;
import com.project.todolist.services.exceptions.DatabaseException;
import com.project.todolist.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repository;
	
	public List<TaskVO> findAll(){
		List<TaskVO> entity = DozerMapper.parseListObjects(repository.findAll(), TaskVO.class);
		
		return entity;
	}
	
	public TaskVO findById(Long id) {
		var entities = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		return DozerMapper.parseObject(entities, TaskVO.class);
	}
	
	public List<TaskVO> findByTaskStatus(TaskStatus taskStatus) {
		List<TaskVO> entities = DozerMapper.parseListObjects(
				repository.findByTaskStatus(taskStatus.getCode()), TaskVO.class);
		
		return entities;
	}
	
	public TaskVO insert(TaskVO taskVO) {
		var entity = DozerMapper.parseObject(taskVO, Task.class);
		
		var vo = DozerMapper.parseObject(repository.save(entity), TaskVO.class);
		
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
		try {
			Task entity = repository.findById(id).get();
			
			var taskEntity = DozerMapper.parseObject(taskVO, Task.class);
			
			updateData(entity, taskEntity);
			
			var vo = DozerMapper.parseObject(repository.save(entity), TaskVO.class);
			
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

package com.project.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.todolist.data.vo.v1.TaskVO;
import com.project.todolist.enums.TaskStatus;
import com.project.todolist.repositories.TaskRepository;
import com.project.todolist.services.exceptions.DatabaseException;
import com.project.todolist.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repository;
	
	public List<TaskVO> findAll(){
		List<TaskVO> tasks = repository.findAll();
		
		return tasks;
	}
	
	public TaskVO findById(Long id) {
		Optional<TaskVO> TaskVO = repository.findById(id);
		
		return TaskVO.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<TaskVO> findByTaskStatus(TaskStatus taskStatus) {
		return repository.findByTaskStatus(taskStatus.getCode());
	}
	
	public TaskVO insert(TaskVO TaskVO) {
		return repository.save(TaskVO);
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
	
	public TaskVO update(Long id, TaskVO TaskVO) {
		try {
			TaskVO entity = repository.findById(id).get();
			updateData(entity, TaskVO);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(TaskVO entity, TaskVO TaskVO) {
		entity.setName(TaskVO.getName());
		entity.setDescription(TaskVO.getDescription());
		entity.setTaskStatus(TaskVO.getTaskStatus());
	}
}

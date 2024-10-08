package com.project.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.todolist.enums.TaskStatus;
import com.project.todolist.models.Task;
import com.project.todolist.repositories.TaskRepository;
import com.project.todolist.services.exceptions.DatabaseException;
import com.project.todolist.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repository;
	
	public List<Task> findAll(){
		List<Task> tasks = repository.findAll();
		
		return tasks;
	}
	
	public Task findById(Long id) {
		Optional<Task> task = repository.findById(id);
		
		return task.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Task> findByTaskStatus(TaskStatus taskStatus) {
		return repository.findByTaskStatus(taskStatus.getCode());
	}
	
	public Task insert(Task task) {
		return repository.save(task);
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
	
	public Task update(Long id, Task task) {
		try {
			Task entity = repository.findById(id).get();
			updateData(entity, task);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Task entity, Task task) {
		entity.setName(task.getName());
		entity.setDescription(task.getDescription());
		entity.setTaskStatus(task.getTaskStatus());
	}
}

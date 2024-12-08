package com.project.todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.todolist.models.Task;
import com.project.todolist.repositories.TaskRepository;
import com.project.todolist.services.exceptions.DatabaseException;
import com.project.todolist.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {
	
	@Autowired
	public TaskRepository repository;
	
	public List<Task> findAllUserTasks(Long userId) {
		List<Task> list = repository.findByUserId(userId);
		return list;
	}
	
	public Task findTaskById(Long taskId, Long userId) {
		Task entity = repository.findByIdAndUserId(taskId, userId)
				.orElseThrow(() -> new ResourceNotFoundException("Task with ID " + taskId +
						" or User with id " + userId +" not found"));
		return entity;
	}
	
	public Task create(Task obj) {
		obj = repository.save(obj);
		return obj;
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Task with ID " + id + " not found");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Task update(Long id, Task obj) {
		try {
			Task entity = repository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Task with ID " + id + " not found"));
			updateData(entity, obj);
			entity = repository.save(entity);
			return entity;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Task with ID " + id + " not found");
		}
	}

	private void updateData(Task entity, Task obj) {
		entity.setTitle(obj.getTitle());
		entity.setDescription(obj.getDescription());
		entity.setStatus(obj.getStatus());
	}
}

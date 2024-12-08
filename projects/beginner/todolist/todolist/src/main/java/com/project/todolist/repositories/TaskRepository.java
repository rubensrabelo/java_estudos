package com.project.todolist.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.todolist.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	List<Task> findByUserId(Long userId);
	
	Optional<Task> findByIdAndUserId(Long taskId, Long userId);
	
	void deleteTaskByIdAndUser(Long taskId, Long userId);
}

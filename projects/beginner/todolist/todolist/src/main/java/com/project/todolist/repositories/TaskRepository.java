package com.project.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.todolist.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

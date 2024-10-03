package com.project.todolist.model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.project.todolist.enums.TaskStatus;

public class Task {
	
	private Long id;
	private String name;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private Integer taskStatus;
	
	public Task() {
	}

	public Task(String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt, TaskStatus taskStatus) {
		super();
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		setTaskStatus(taskStatus);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public TaskStatus getTaskStatus() {
		return TaskStatus.valueOf(taskStatus);
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		if(taskStatus != null) 
			this.taskStatus = taskStatus.getCode();
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, description, id, name, taskStatus, updatedAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(taskStatus, other.taskStatus) && Objects.equals(updatedAt, other.updatedAt);
	}
}

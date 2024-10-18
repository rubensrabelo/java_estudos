package com.project.todolist.integrationtests.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.project.todolist.enums.TaskStatus;

public class TaskVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;	
	private String name;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private TaskStatus taskStatus;
	
	public TaskVO() {
	}

	public TaskVO(Long id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt,
			TaskStatus taskStatus) {
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.taskStatus = taskStatus;
	}

	public Long getId() {
		return id;
	}

	public void setKey(Long id) {
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
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
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
		TaskVO other = (TaskVO) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name) && taskStatus == other.taskStatus
				&& Objects.equals(updatedAt, other.updatedAt);
	}
}

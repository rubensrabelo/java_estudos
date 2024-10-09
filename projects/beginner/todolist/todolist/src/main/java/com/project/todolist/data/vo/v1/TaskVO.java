package com.project.todolist.data.vo.v1;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.project.todolist.enums.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "task")
public class TaskVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private Integer taskStatus;
	
	public TaskVO() {
	}

	public TaskVO(String name, String description, TaskStatus taskStatus) {
		super();
		this.name = name;
		this.description = description;
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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public TaskStatus getTaskStatus() {
		return TaskStatus.valueOf(taskStatus);
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		if(taskStatus != null) 
			this.taskStatus = taskStatus.getCode();
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
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
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(taskStatus, other.taskStatus) && Objects.equals(updatedAt, other.updatedAt);
	}
}

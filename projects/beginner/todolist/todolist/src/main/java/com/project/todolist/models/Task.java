package com.project.todolist.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.project.todolist.models.enums.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	
	@CreationTimestamp
	@Column(name = "created_at", updatable = false, nullable = false)
	private LocalDate createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDate updatedAt;
	
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Task() {
	}

	public Task(Long id, String title, String description, LocalDate createdAt, LocalDate updatedAt,
			TaskStatus status, User user) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		setStatus(status);
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public TaskStatus getStatus() {
		return TaskStatus.valueOf(status);
	}

	public void setStatus(TaskStatus taskStatus) {
		if(taskStatus != null)
			this.status = taskStatus.getCode();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, description, id, status, title, updatedAt);
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
				&& Objects.equals(id, other.id) && Objects.equals(status, other.status)
				&& Objects.equals(title, other.title) && Objects.equals(updatedAt, other.updatedAt);
	}
}

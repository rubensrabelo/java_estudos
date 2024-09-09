package com.productManagement.productManagement.models;

import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100, unique = true)
	private String name;

	private String description;
	
	@Column(nullable = false)
	private Double unitPrice;
	
	private Double quantity;

	@Column(nullable = false, updatable = false)
	private Instant createdDate;
	
	@Column(nullable = false)
	private Instant updatedDate;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	public Product() {
	}

	public Product(Long id, String name, String description, Double unitPrice, Double quantity, Category category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.category = category;
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

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public Instant getUpdatedDate() {
		return updatedDate;
	}
	
	@PrePersist
	public void prePersist() {
		Instant now = Instant.now();
		
		this.createdDate = now;
		this.updatedDate = now;
	}
	
	@PreUpdate
	public void preUpdate() {
		this.updatedDate = Instant.now();
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, createdDate, description, id, name, quantity, unitPrice, updatedDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(category, other.category) && Objects.equals(createdDate, other.createdDate)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(quantity, other.quantity)
				&& Objects.equals(unitPrice, other.unitPrice) && Objects.equals(updatedDate, other.updatedDate);
	}
}

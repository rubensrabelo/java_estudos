package com.productManagement.productManagement.models;

import java.time.Instant;
import java.util.Objects;

public class Product {
	
	private Long id;
	private String name;
	private String description;
	private Double unit_price;
	private Double quantity;
	private Long category_id;
	private Instant created_date;
	private Instant uptated_date;
	
	public Product() {
	}

	public Product(Long id, String name, String description, Double unit_price, Double quantity, Long category_id,
			Instant created_date, Instant uptated_date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.unit_price = unit_price;
		this.quantity = quantity;
		this.category_id = category_id;
		this.created_date = created_date;
		this.uptated_date = uptated_date;
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

	public Double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public Instant getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Instant created_date) {
		this.created_date = created_date;
	}

	public Instant getUptated_date() {
		return uptated_date;
	}

	public void setUptated_date(Instant uptated_date) {
		this.uptated_date = uptated_date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category_id, created_date, description, id, name, quantity, unit_price, uptated_date);
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
		return Objects.equals(category_id, other.category_id) && Objects.equals(created_date, other.created_date)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(quantity, other.quantity)
				&& Objects.equals(unit_price, other.unit_price) && Objects.equals(uptated_date, other.uptated_date);
	}
}

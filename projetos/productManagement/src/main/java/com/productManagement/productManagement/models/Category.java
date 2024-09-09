package com.productManagement.productManagement.models;

import java.util.Objects;

public class Category {
	
	private Long id;
	private String name;
	private String desciption;
	
	public Category() {
	}

	public Category(Long id, String name, String desciption) {
		this.id = id;
		this.name = name;
		this.desciption = desciption;
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

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	@Override
	public int hashCode() {
		return Objects.hash(desciption, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(desciption, other.desciption) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}
}

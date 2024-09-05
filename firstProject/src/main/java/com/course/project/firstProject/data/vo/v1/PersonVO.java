package com.course.project.firstProject.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

public class PersonVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String firstname;
	private String lastname;
	private String address;
	private String gender;
	
	public PersonVO() {
	}
	
	public PersonVO(Long id, String firstname, String lastname, String adress, String gender) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = adress;
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, firstname, gender, id, lastname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonVO other = (PersonVO) obj;
		return Objects.equals(address, other.address) && Objects.equals(firstname, other.firstname)
				&& Objects.equals(gender, other.gender) && Objects.equals(id, other.id)
				&& Objects.equals(lastname, other.lastname);
	}
}

package com.system.management.customer.model;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Long id;
	public String street;
	public int number;
	public String complement;
	public String neighborhood;
	public String city;
	public String state;
	public String postalCode;
	public String country;
	
	public Address() {
	}

	public Address(String street, int number, String complement, String neighborhood, String city,
			String state, String postalCode, String country) {
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.neighborhood = neighborhood;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, complement, country, id, neighborhood, number, postalCode, state, street);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(complement, other.complement)
				&& Objects.equals(country, other.country) && Objects.equals(id, other.id)
				&& Objects.equals(neighborhood, other.neighborhood) && number == other.number
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(state, other.state)
				&& Objects.equals(street, other.street);
	}
}

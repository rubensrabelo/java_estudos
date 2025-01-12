package med.voll.api.patient;

import java.util.Objects;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import med.voll.api.address.Address;
import med.voll.api.doctor.DataRegistrationMedic;

@Entity
@Table(name = "patient")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phoneNumber;
	private String cpf;
	
	@Embedded
	private Address address;
	private Boolean activate;
	
	public Patient() {
	}
	
	public Patient(PatientRegisterData data) {
		this.name = data.name();
		this.email = data.email();
		this.phoneNumber = data.phoneNumber();
		this.cpf = data.cpf();
		// this.address = new Address(data.address());
		this.activate = true;
	}

	public Patient(Long id, String name, String email, String phoneNumber, String cpf, Address address) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.cpf = cpf;
		this.address = address;
		this.activate = true;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Boolean getActivate() {
		return activate;
	}

	public void setActivate(Boolean activate) {
		this.activate = activate;
	}
	
	 public void updateData(PatientUpdateData data) {
	        if (data.name() != null) {
	            this.name = data.name();
	        }
	        if (data.phoneNumber() != null) {
	            this.phoneNumber = data.phoneNumber();
	        }
	        if (data.address() != null) {
	            this.address.updateInformation(data.address());;
	        }

	    }

	    public void desable() {
	        this.activate = false;
	    }

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}
}

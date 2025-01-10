package med.voll.api.address;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String street;
	private String neighborhood;
	private String postalCode;
	private String number;
	private String additionalInfo;
	private String city;
	private String state;
	
	public Address() {
	}

	public Address(String street, String neighborhood, String postalCode, String number, String additionalInfo,
			String city, String state) {
		this.street = street;
		this.neighborhood = neighborhood;
		this.postalCode = postalCode;
		this.number = number;
		this.additionalInfo = additionalInfo;
		this.city = city;
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
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
	
	public void updateInformation(AddressData data) {
		if (data.street() != null) {
            this.street = data.street();
        }
        if (data.neighborhood() != null) {
            this.neighborhood = data.neighborhood();
        }
        if (data.postalCode() != null) {
            this.postalCode = data.postalCode();
        }
        if (data.state() != null) {
            this.state = data.state();
        }
        if (data.city() != null) {
            this.city = data.city();
        }
        if (data.number() != null) {
            this.number = data.number();
        }
        if (data.additionalInfo() != null) {
            this.additionalInfo = data.additionalInfo();
        }
	}
}

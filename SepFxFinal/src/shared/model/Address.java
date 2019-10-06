package shared.model;

import java.io.Serializable;

public class Address implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private int addressId; 
	private String street;
	private String houseNumber;
	private String city;
	private String postcode;
	private String country;
	
	public Address()
	{
		
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getAddressId() {
		return addressId;
	}


	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	

}

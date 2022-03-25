package ch.fhnw.lems.persistence;

// LUM
public class Adress {
	private Long adressId;
	private String street;
	private Integer postalCode;
	private String city;
	private Country country;

	public Long getAdressId() {
		return adressId;
	}

	public void setAdressId(Long adressId) {
		this.adressId = adressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
package ch.fhnw.lems.persistence;

// LUM
public class Country {
	private Long countyId;
	private String country;

	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
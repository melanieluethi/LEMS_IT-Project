package ch.fhnw.lems.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// LUM
@Entity
public class Shipping {
	@Id
	@GeneratedValue
	private Long shippingId;
	private String shippingMethod;
	private Double shippingStandardCost;
	private Double shippingExpressCost;
	private Double shippingPackageCost;

	public Long getShippingId() {
		return shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public Double getShippingStandardCost() {
		return shippingStandardCost;
	}

	public void setShippingStandardCost(Double shippingStandardCost) {
		this.shippingStandardCost = shippingStandardCost;
	}

	public Double getShippingExpressCost() {
		return shippingExpressCost;
	}

	public void setShippingExpressCost(Double shippingExpressCost) {
		this.shippingExpressCost = shippingExpressCost;
	}

	public Double getShippingPackageCost() {
		return shippingPackageCost;
	}

	public void setShippingPackageCost(Double shippingPackageCost) {
		this.shippingPackageCost = shippingPackageCost;
	}
}
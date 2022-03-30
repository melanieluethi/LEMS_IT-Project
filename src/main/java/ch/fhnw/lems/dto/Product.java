package ch.fhnw.lems.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

// LUM
@Entity
public class Product {
	@Id
	@GeneratedValue
	private Long productId;
	private String Description;
	private Double price;
	private Integer discount;
	@Lob
	private Byte[] productImg;
	

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Byte[] getProductImg() {
		return productImg;
	}

	public void setProductImg(Byte[] productImg) {
		this.productImg = productImg;
	}
}
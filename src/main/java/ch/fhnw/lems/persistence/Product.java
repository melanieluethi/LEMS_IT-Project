package ch.fhnw.lems.persistence;

import javax.imageio.ImageIO;

// LUM
public class Product {
	private Long productId;
	private String Description;
	private Double price;
	private Integer discount;
	private ImageIO productImg;

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

	public ImageIO getProductImg() {
		return productImg;
	}

	public void setProductImg(ImageIO productImg) {
		this.productImg = productImg;
	}
}
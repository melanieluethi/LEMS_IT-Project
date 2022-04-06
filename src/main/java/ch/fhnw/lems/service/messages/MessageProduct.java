package ch.fhnw.lems.service.messages;

// LUM
public class MessageProduct {
	private String description;
	private Double price;
	private Integer discount;
	private Byte[] productImg;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
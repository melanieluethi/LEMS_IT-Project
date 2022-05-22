package ch.fhnw.lems.controller.messages;

//LUM
public class MessageResultProduct extends MessageResultStandard {
	private String productName;
	private String productNameEng;
	private String description;
	private String descriptionEng;
	private Double price;
	private Integer discount;
	private String productImg;

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

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNameEng() {
		return productNameEng;
	}

	public void setProductNameEng(String productNameEng) {
		this.productNameEng = productNameEng;
	}

	public String getDescriptionEng() {
		return descriptionEng;
	}

	public void setDescriptionEng(String descriptionEng) {
		this.descriptionEng = descriptionEng;
	}
}
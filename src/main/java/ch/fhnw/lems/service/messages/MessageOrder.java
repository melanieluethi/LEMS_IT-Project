package ch.fhnw.lems.service.messages;

import java.util.ArrayList;

import ch.fhnw.lems.dto.Product;

// LUM
public class MessageOrder {
	private String userId;
	private ArrayList<Product> products;
	private Long shippingId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public Long getShippingId() {
		return shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}
}
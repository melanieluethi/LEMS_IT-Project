package ch.fhnw.lems.persistence;

import java.util.Map;

// LUM
public class Order {
	private Long orderId;
	private User user;
	private Map<Product, Integer> products;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}
}
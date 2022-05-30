package ch.fhnw.lems.controller.messages;

import java.util.List;

//LUM
public class MessageChangeOrder {
	private Long orderId;
	private Long userId;
	private List<List<String>> orderItems;
	private Long shippingId;
	private String shippingMethod;
	private Double shippingCost;
	private Double totalPrice;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<List<String>> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<List<String>> orderItems) {
		this.orderItems = orderItems;
	}

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

	public Double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(Double shippingCost) {
		this.shippingCost = shippingCost;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
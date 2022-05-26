package ch.fhnw.lems.controller.messages;

import java.util.List;

import ch.fhnw.lems.entity.OrderItem;

// LUM
public class MessageOrder {
	private List<OrderItem> orderItems;
	private Long shippingId;
	private Double totalPrice;

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Long getShippingId() {
		return shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
package ch.fhnw.lems.service.messages;

import java.util.List;

import ch.fhnw.lems.dto.OrderItem;

// LUM
public class MessageOrder {
	private Long userId;
	private List<OrderItem> orderItems;
	private Long shippingId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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
}
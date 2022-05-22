package ch.fhnw.lems.controller.messages;

import java.util.List;

import ch.fhnw.lems.entity.Shipping;

//LUM
public class MessageChangeOrder {
	private Long orderId;
	private Long userId;
	private List<List<String>> orderItems;
	private Shipping shipping;
	private Integer totalPrice;

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

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
}
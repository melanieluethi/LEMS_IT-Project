package ch.fhnw.lems.controller.messages;

import java.util.List;

import ch.fhnw.lems.entity.OrderItem;
import ch.fhnw.lems.entity.Shipping;
import ch.fhnw.lems.entity.User;

// LUM
public class MessageResultShoppingCard extends MessageResultStandard {
	private User user;
	private List<OrderItem> orderItems;
	private Shipping shipping;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}
}
package ch.fhnw.lems.controller.messages;

import java.util.List;

import ch.fhnw.lems.entity.OrderItemCart;
import ch.fhnw.lems.entity.Shipping;
import ch.fhnw.lems.entity.User;

// LUM
public class MessageResultShoppingCart extends MessageResultStandard {
	private User user;
	private List<OrderItemCart> orderItems;
	private Shipping shipping;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItemCart> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemCart> orderItems) {
		this.orderItems = orderItems;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}
}
package ch.fhnw.lems.service.messages;

import java.util.List;

import ch.fhnw.lems.dto.OrderItem;
import ch.fhnw.lems.dto.Shipping;
import ch.fhnw.lems.dto.User;

// LUM
public class MessageResultOrder extends MessageResultStandard {
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
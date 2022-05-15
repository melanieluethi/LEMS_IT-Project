package ch.fhnw.lems.controller.messages;

import java.util.List;

import ch.fhnw.lems.entity.CustomerOrder;
import ch.fhnw.lems.entity.OrderItem;
import ch.fhnw.lems.entity.Shipping;
import ch.fhnw.lems.entity.User;

// LUM
public class MessageResultOrder extends MessageResultStandard {
	private User user;
	private CustomerOrder order;
	private List<OrderItem> orderItems;
	private Shipping shipping;
	private Double totalPrice;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public CustomerOrder getOrder() {
		return order;
	}

	public void setOrder(CustomerOrder order) {
		this.order = order;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
package ch.fhnw.lems.service.messages;

import java.util.List;

import ch.fhnw.lems.dto.OrderItem;
import ch.fhnw.lems.dto.Shipping;

// LUM
public class MessageResultShoppingCard extends MessageResultStandard {
	private List<OrderItem> orderItems;
	private Shipping shipping;

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
package ch.fhnw.lems.controller.messages;

import ch.fhnw.lems.entity.CustomerOrder;

// LUM
public class MessageResultOrder extends MessageResultStandard {
	private CustomerOrder order;

	public CustomerOrder getOrder() {
		return order;
	}

	public void setOrder(CustomerOrder order) {
		this.order = order;
	}
}
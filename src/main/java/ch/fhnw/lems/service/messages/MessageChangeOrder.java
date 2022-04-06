package ch.fhnw.lems.service.messages;

//LUM
public class MessageChangeOrder extends MessageOrder {
	private Long orderId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
}
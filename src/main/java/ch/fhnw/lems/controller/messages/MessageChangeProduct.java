package ch.fhnw.lems.controller.messages;

//LUM
public class MessageChangeProduct extends MessageProduct {
	private Long productId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
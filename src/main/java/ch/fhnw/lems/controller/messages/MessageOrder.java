package ch.fhnw.lems.controller.messages;

// LUM
public class MessageOrder {
	private Long cartId;
	private Long shippingId;
	private String shippingMethod;
	private Boolean deliveryAvailable;
	private Boolean deliveryExpressAvailable;
	private Double totalPrice;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getShippingId() {
		return shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	
	public Boolean getDeliveryAvailable() {
		return deliveryAvailable;
	}

	public void setDeliveryAvailable(Boolean deliveryAvailable) {
		this.deliveryAvailable = deliveryAvailable;
	}

	public Boolean getDeliveryExpressAvailable() {
		return deliveryExpressAvailable;
	}

	public void setDeliveryExpressAvailable(Boolean deliveryExpressAvailable) {
		this.deliveryExpressAvailable = deliveryExpressAvailable;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
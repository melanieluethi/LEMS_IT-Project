package ch.fhnw.lems.controller.messages;

// LUM
public class MessageTransportCostCart {
	private Long cartId;
	private String shippingMethod;
	private int amountProduct1;
	private int amountProduct2;
	private int amountProduct3;
	private int amountProduct4;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public int getAmountProduct1() {
		return amountProduct1;
	}

	public void setAmountProduct1(int amountProduct1) {
		this.amountProduct1 = amountProduct1;
	}

	public int getAmountProduct2() {
		return amountProduct2;
	}

	public void setAmountProduct2(int amountProduct2) {
		this.amountProduct2 = amountProduct2;
	}

	public int getAmountProduct3() {
		return amountProduct3;
	}

	public void setAmountProduct3(int amountProduct3) {
		this.amountProduct3 = amountProduct3;
	}

	public int getAmountProduct4() {
		return amountProduct4;
	}

	public void setAmountProduct4(int amountProduct4) {
		this.amountProduct4 = amountProduct4;
	}
}
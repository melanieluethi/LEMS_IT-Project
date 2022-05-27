package ch.fhnw.lems.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

// LUM
@Entity
public class Cart {
	@Id
	@GeneratedValue
	private Long cartId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany
	@JoinColumn(name = "cart_id")
	private List<OrderItemCart> orderItems;

	@ManyToOne
	@JoinColumn(name = "shipping_id")
	private Shipping shipping;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

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
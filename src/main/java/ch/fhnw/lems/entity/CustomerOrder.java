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
public class CustomerOrder {
	@Id
	@GeneratedValue
	private Long orderId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany
	@JoinColumn(name = "customer_order_id")
	private List<OrderItemOrder> orderItems;

	@ManyToOne
	@JoinColumn(name = "shipping_id")
	private Shipping shipping;

	private Double totalPrice;
	private Boolean deliveryAvailable;
	private Boolean deliveryExpressAvailable;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItemOrder> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemOrder> orderItems) {
		this.orderItems = orderItems;
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
}
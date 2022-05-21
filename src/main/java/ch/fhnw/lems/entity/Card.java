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
public class Card {
	@Id
	@GeneratedValue
	private Long cardId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany
	@JoinColumn(name = "card_id")
	private List<OrderItem> orderItems;

	@ManyToOne
	@JoinColumn(name = "shipping_id")
	private Shipping shipping;

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

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
package ch.fhnw.lems.controller.order;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.lems.controller.messages.MessageAddToCard;
import ch.fhnw.lems.controller.messages.MessageResultShoppingCard;
import ch.fhnw.lems.entity.Card;
import ch.fhnw.lems.entity.OrderItem;
import ch.fhnw.lems.entity.Product;
import ch.fhnw.lems.entity.User;
import ch.fhnw.lems.persistence.CardRepository;
import ch.fhnw.lems.persistence.OrderItemRepository;
import ch.fhnw.lems.persistence.ProductRepository;
import ch.fhnw.lems.persistence.UserRepository;

// LUM
@RestController
public class CardService {
	Logger logger = LoggerFactory.getLogger(CardService.class);
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
		
	@PostMapping(path= "/api/addProductToCard", produces = " application/json")
	public boolean addProductToCard(@RequestBody MessageAddToCard msgAddToCard) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);		
		
		Long cartId = cardRepository.findByUser(currentUser.getUserId());
		Card cart;
		if(cartId == null) {
			cart = new Card();
			cart.setUser(currentUser);
		} else {
			cart = cardRepository.findById(cartId).get();
		}
		
		Product product = productRepository.findById(msgAddToCard.getProductId()).get();		
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		Integer quantity = msgAddToCard.getQuantity();
		orderItem.setQuantity(quantity);
		OrderItem savedOrderItem = orderItemRepository.save(orderItem);
		
		List<OrderItem> orderItems = new ArrayList<>();
		if(cart.getOrderItems() != null) {
			orderItems.addAll(cart.getOrderItems());	
		}
		orderItems.add(savedOrderItem);
		cart.setOrderItems(orderItems);
				
		cardRepository.save(cart);
		
		logger.info("Adding OrderItem: " + orderItem.getProduct().getProductName() + " to Card.");
		return true;
	}
	
	@GetMapping(path= "/api/shoppingCard", produces = " application/json")
	public MessageResultShoppingCard getShoppingCard() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);		
		
		Long cardId = cardRepository.findByUser(currentUser.getUserId());
		Card card = cardRepository.findById(cardId).get();
		
		MessageResultShoppingCard msgResult = new MessageResultShoppingCard();
		msgResult.setOrderItems(card.getOrderItems());
		msgResult.setShipping(card.getShipping());
		
		logger.info("Get Card of: " + card.getUser().getUsername());
		return msgResult;
	}
}
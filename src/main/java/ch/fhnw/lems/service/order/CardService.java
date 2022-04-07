package ch.fhnw.lems.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.lems.dto.Card;
import ch.fhnw.lems.dto.OrderItem;
import ch.fhnw.lems.dto.Product;
import ch.fhnw.lems.dto.User;
import ch.fhnw.lems.persistence.CardRepository;
import ch.fhnw.lems.persistence.OrderItemRepository;
import ch.fhnw.lems.persistence.ProductRepository;
import ch.fhnw.lems.persistence.UserRepository;
import ch.fhnw.lems.service.messages.MessageAddToCard;
import ch.fhnw.lems.service.messages.MessageResultShoppingCard;

// LUM
@RestController
public class CardService {
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping(path= "/api/addProductToCard", produces = " application/json")
	public boolean addProductToCard(@RequestBody MessageAddToCard msgAddToCard) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);				
		Card card = cardRepository.findByUser(currentUser.getUserId());
		Product product = productRepository.getById(msgAddToCard.getProductId());
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItem.setQuantity(msgAddToCard.getQuantity());
		OrderItem savedOrderItem = orderItemRepository.save(orderItem);
		card.getOrderItems().add(savedOrderItem);
		cardRepository.save(card);
		return true;
	}
	
	@GetMapping(path= "/api/shoppingCard", produces = " application/json")
	public MessageResultShoppingCard getShoppingCard() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);		
		Card card = cardRepository.findByUser(currentUser.getUserId());
		MessageResultShoppingCard msgResult = new MessageResultShoppingCard();
		msgResult.setOrderItems(card.getOrderItems());
		msgResult.setShipping(card.getShipping());
		return msgResult;
	}
}
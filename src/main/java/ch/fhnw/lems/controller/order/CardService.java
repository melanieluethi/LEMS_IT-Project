package ch.fhnw.lems.controller.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.lems.business.PriceCalculationExpress;
import ch.fhnw.lems.controller.messages.MessageAddToCard;
import ch.fhnw.lems.controller.messages.MessageResultShoppingCard;
import ch.fhnw.lems.controller.messages.MessageResultTransportCost;
import ch.fhnw.lems.controller.messages.MessageTransportCost;
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
	
	@GetMapping(path= "/api/addProductToCard", produces = " application/json")
	public boolean addProductToCard(@RequestBody MessageAddToCard msgAddToCard) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);		
		
		Card card = cardRepository.findByUser(currentUser.getUserId());
		Product product = productRepository.findById(msgAddToCard.getProductId()).get();
		
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItem.setQuantity(msgAddToCard.getQuantity());
		
		OrderItem savedOrderItem = orderItemRepository.save(orderItem);
		card.getOrderItems().add(savedOrderItem);
		cardRepository.save(card);
		
		logger.info("Adding OrderItem: " + orderItem.getProduct().getProductName() + " to Card.");
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
		
		logger.info("Get Card of: " + card.getUser().getUsername());
		return msgResult;
	}
	
	// The Transportcost need a cardId or a orderId to Calculate the transport cost
	@GetMapping(path = "/api/transportCost", produces = " application/json")
	public MessageResultTransportCost getTransportCost(@RequestBody MessageTransportCost msgTransportCost) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);	
		
		MessageResultTransportCost msgResult = new MessageResultTransportCost();
		// TODO LUM/HIS getTransportCost
		msgResult.setTransportCostStandard(120d);
		
		PriceCalculationExpress express = new PriceCalculationExpress();		
		double expressPrice = express.calculateExpressPrice(currentUser.getPostalCode(), 0);
		msgResult.setTransportCostExpress(expressPrice);
		
		return msgResult;
	}
}
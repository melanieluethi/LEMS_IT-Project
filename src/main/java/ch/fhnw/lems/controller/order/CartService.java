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

import ch.fhnw.lems.controller.messages.MessageAddToCart;
import ch.fhnw.lems.controller.messages.MessageResultShoppingCart;
import ch.fhnw.lems.entity.Cart;
import ch.fhnw.lems.entity.OrderItem;
import ch.fhnw.lems.entity.Product;
import ch.fhnw.lems.entity.User;
import ch.fhnw.lems.persistence.CartRepository;
import ch.fhnw.lems.persistence.OrderItemRepository;
import ch.fhnw.lems.persistence.ProductRepository;
import ch.fhnw.lems.persistence.UserRepository;

// LUM
@RestController
public class CartService {
	Logger logger = LoggerFactory.getLogger(CartService.class);
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
		
	@PostMapping(path= "/api/addProductToCart", produces = " application/json")
	public boolean addProductToCart(@RequestBody MessageAddToCart msgAddToCart) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);		
		
		Long cartId = cartRepository.findByUser(currentUser.getUserId());
		Cart cart;
		if(cartId == null) {
			cart = new Cart();
			cart.setUser(currentUser);
		} else {
			cart = cartRepository.findById(cartId).get();
		}
		
		List<OrderItem> orderItems = new ArrayList<>();
		if(cart.getOrderItems() != null) {
			orderItems.addAll(cart.getOrderItems());
			OrderItem orderItemExist = orderItems.stream()
					  .filter(o -> o.getProduct().getProductId().equals((msgAddToCart.getProductId())))
					  .findAny()
					  .orElse(null);
			
			if(orderItemExist != null) {
				int indexOfExistingOrderItem = orderItems.indexOf(orderItemExist);
				Integer newQuantity = orderItemExist.getQuantity() + msgAddToCart.getQuantity();
				orderItems.get(indexOfExistingOrderItem).setQuantity(newQuantity);
				logger.info("Change Quantity of OrderItem: " + orderItemExist.getProduct().getProductName() + " in Cart.");
			} else {
				OrderItem orderItem = saveNewOrderItem(msgAddToCart.getProductId(), msgAddToCart.getQuantity());
				orderItems.add(orderItem);
				logger.info("Adding OrderItem: " + orderItem.getProduct().getProductName() + " to Cart.");	
			}
		} else {
			OrderItem orderItem = saveNewOrderItem(msgAddToCart.getProductId(), msgAddToCart.getQuantity());
			orderItems.add(orderItem);
			logger.info("Adding OrderItem: " + orderItem.getProduct().getProductName() + " to Cart.");	
		}
		
		cart.setOrderItems(orderItems);			
		cartRepository.save(cart);
		
		return true;
	}
	
	private OrderItem saveNewOrderItem(Long productId,Integer quantity) {
		Product product = productRepository.findById(productId).get();		
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItem.setQuantity(quantity);
		return orderItemRepository.save(orderItem);
	}
	
	@GetMapping(path= "/api/shoppingCart", produces = " application/json")
	public MessageResultShoppingCart getShoppingCart() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);		
		
		MessageResultShoppingCart msgResult = new MessageResultShoppingCart();
		Long cartId = cartRepository.findByUser(currentUser.getUserId());
		if(cartId != null) {
			Cart cart = cartRepository.findById(cartId).get();	
			msgResult.setId(cart.getCartId());
			msgResult.setUser(currentUser);
			msgResult.setOrderItems(cart.getOrderItems());
			msgResult.setShipping(cart.getShipping());
			msgResult.setSuccessful(true);
			logger.info("Get Cart of: " + cart.getUser().getUsername());
		} else {
			msgResult.setSuccessful(false);
			logger.info("No Cart for " + username + " exist.");
		}
		return msgResult;
	}
}
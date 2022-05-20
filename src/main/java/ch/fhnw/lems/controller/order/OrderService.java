package ch.fhnw.lems.controller.order;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.lems.controller.messages.MessageChangeOrder;
import ch.fhnw.lems.controller.messages.MessageOrder;
import ch.fhnw.lems.controller.messages.MessageResultOrder;
import ch.fhnw.lems.entity.CustomerOrder;
import ch.fhnw.lems.entity.OrderItem;
import ch.fhnw.lems.entity.Product;
import ch.fhnw.lems.entity.User;
import ch.fhnw.lems.entity.UserRole;
import ch.fhnw.lems.persistence.OrderItemRepository;
import ch.fhnw.lems.persistence.OrderRepository;
import ch.fhnw.lems.persistence.ProductRepository;
import ch.fhnw.lems.persistence.UserRepository;

//LUM
@RestController
public class OrderService {
	Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping (path = "/api/order", produces = "application/json")
	public boolean createOrder(@RequestBody MessageOrder msgOrder) {
		CustomerOrder order = new CustomerOrder();
		User user = userRepository.findById(msgOrder.getUserId()).get();
		order.setUser(user);
		order.setOrderItems(msgOrder.getOrderItems());
		order.setTotalPrice();
		orderRepository.save(order);	
		logger.info("Create order " + order.getOrderId() + " was successful.");
		return true;
	}
	
	@PutMapping(path = "/api/order", produces = "application/json")
	public boolean changeOrder(@RequestBody MessageChangeOrder msgOrder) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);		
		if(currentUser.getRole().getRole().equals(UserRole.ADMIN)) {
			CustomerOrder order = orderRepository.findById(msgOrder.getOrderId()).get();			
			User user = userRepository.findById(msgOrder.getUserId()).get();
			order.setUser(user);
			
			List<OrderItem> orderItems = new ArrayList<>();
			for (int i = 0; i < msgOrder.getOrderItems().size(); i++) {
				List<String> orderItemsValues = msgOrder.getOrderItems().get(i);
				OrderItem orderItem = orderItemRepository.findById(Long.valueOf(orderItemsValues.get(0))).get();
				Product product = productRepository.findByProductName(orderItemsValues.get(1));
				product.setPrice(Double.valueOf(orderItemsValues.get(2)));
				product.setDiscount(Integer.valueOf(orderItemsValues.get(3)));
				orderItem.setProduct(product);
				orderItem.setQuantity(Integer.valueOf(orderItemsValues.get(4)));
				orderItems.add(orderItem);
			}
			
			order.setOrderItems(orderItems);
			order.setShipping(msgOrder.getShipping());
			order.setTotalPrice();
			orderRepository.save(order);
			logger.info("Change order " + order.getOrderId() + " was successful.");
			return true;
		} else {
			logger.info("Change order was not successful.");
			return false;
		}
	}
	
	@GetMapping(path= "api/order/{orderId}", produces = " application/json")
	public MessageResultOrder getOrder(@PathVariable Long orderId){
		CustomerOrder order = orderRepository.findById(orderId).get();
		logger.info("Search for order: " + order.getOrderId());		
		MessageResultOrder msgResultOrder = new MessageResultOrder();
		msgResultOrder.setId(orderId);
		msgResultOrder.setOrder(order);
		msgResultOrder.setSuccessful(true);
		return msgResultOrder;
	}
	
	@GetMapping(path= "/api/orders/{userId}", produces = " application/json")
	public ArrayList<MessageResultOrder> getOrderByUserId(@PathVariable Long userId){
		ArrayList<MessageResultOrder> results = new ArrayList<>();
		ArrayList<CustomerOrder> orders = orderRepository.findByUserId(userId);		
		orders.forEach(o -> {
			MessageResultOrder msgResultOrder = new MessageResultOrder();
			msgResultOrder.setId(o.getOrderId());
			msgResultOrder.setOrder(o);
			results.add(msgResultOrder);
			logger.info("Get order " + o.getOrderId());
		});			
		return results;
	}
	
	@GetMapping(path= "/api/orders", produces = " application/json")
	public ArrayList<MessageResultOrder> getOrders(){
		ArrayList<MessageResultOrder> results = new ArrayList<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);		
		if(currentUser.getRole().getRole().equals(UserRole.ADMIN)) {
			ArrayList<CustomerOrder> orders = (ArrayList<CustomerOrder>) orderRepository.findAll();	
			orders.forEach(o -> {
				MessageResultOrder msgResultOrder = new MessageResultOrder();
				msgResultOrder.setId(o.getOrderId());
				msgResultOrder.setOrder(o);
				results.add(msgResultOrder);
				logger.info("Get order of " + o.getOrderId());
			});				
		}
		return results;
	}
}
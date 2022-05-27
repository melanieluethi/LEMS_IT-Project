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
import ch.fhnw.lems.controller.messages.MessageResultStandard;
import ch.fhnw.lems.entity.Cart;
import ch.fhnw.lems.entity.CustomerOrder;
import ch.fhnw.lems.entity.OrderItemOrder;
import ch.fhnw.lems.entity.Product;
import ch.fhnw.lems.entity.Shipping;
import ch.fhnw.lems.entity.User;
import ch.fhnw.lems.entity.UserRole;
import ch.fhnw.lems.persistence.CartRepository;
import ch.fhnw.lems.persistence.OrderItemCartRepository;
import ch.fhnw.lems.persistence.OrderItemOrderRepository;
import ch.fhnw.lems.persistence.OrderRepository;
import ch.fhnw.lems.persistence.ProductRepository;
import ch.fhnw.lems.persistence.ShippingRepository;
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
	private OrderItemCartRepository orderItemCartRepository;
	
	@Autowired
	private OrderItemOrderRepository orderItemOrderRepository;
	
	@Autowired
	private ShippingRepository shippingRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@PostMapping (path = "/api/order", produces = "application/json")
	public MessageResultStandard createOrder(@RequestBody MessageOrder msgOrder) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);	
		CustomerOrder order = new CustomerOrder();
		order.setUser(currentUser);
		Cart cart = cartRepository.findById(msgOrder.getCartId()).get();
		List<OrderItemOrder> orderItems = new ArrayList<>();
		cart.getOrderItems().forEach(o -> {
			OrderItemOrder orderItem = new OrderItemOrder();
			orderItem.setProduct(o.getProduct());
			orderItem.setQuantity(o.getQuantity());
			orderItem = orderItemOrderRepository.save(orderItem);
			orderItems.add(orderItem);
		});
		order.setOrderItems(orderItems);
		Shipping shipping = cart.getShipping();
		shipping.setShippingMethod(msgOrder.getShippingMethod());
		shipping = shippingRepository.save(shipping);
		order.setShipping(shipping);
		order.setDeliveryAvailable(msgOrder.getDeliveryAvailable());
		order.setDeliveryExpressAvailable(msgOrder.getDeliveryExpressAvailable());
		order.setTotalPrice(msgOrder.getTotalPrice());
		order = orderRepository.save(order);	
		
		orderItemCartRepository.deleteAll(cart.getOrderItems());
		//cart.setShipping(null);
		cartRepository.deleteById(msgOrder.getCartId());
		logger.info("Create order " + order.getOrderId() + " was successful.");
		MessageResultStandard msgResult = new MessageResultStandard();
		msgResult.setId(order.getOrderId());
		msgResult.setSuccessful(true);
		return msgResult;
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
			
			List<OrderItemOrder> orderItems = new ArrayList<>();
			for (int i = 0; i < msgOrder.getOrderItems().size(); i++) {
				List<String> orderItemsValues = msgOrder.getOrderItems().get(i);
				OrderItemOrder orderItem = orderItemOrderRepository.findById(Long.valueOf(orderItemsValues.get(0))).get();
				Product product = productRepository.findByProductName(orderItemsValues.get(1));
				product.setPrice(Double.valueOf(orderItemsValues.get(2)));
				product.setDiscount(Integer.valueOf(orderItemsValues.get(3)));
				orderItem.setProduct(product);
				orderItem.setQuantity(Integer.valueOf(orderItemsValues.get(4)));
				orderItems.add(orderItem);
			}
			
			order.setOrderItems(orderItems);
			order.setShipping(msgOrder.getShipping());
			order.setTotalPrice(msgOrder.getTotalPrice());
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
		} else {
			ArrayList<CustomerOrder> orders = orderRepository.findByUserId(currentUser.getUserId());	
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
package ch.fhnw.lems.controller.transportCost;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.lems.business.DistanceCalculation;
import ch.fhnw.lems.business.PriceCalculationExpress;
import ch.fhnw.lems.business.PriceCalculationPackage;
import ch.fhnw.lems.business.PriceCalculationStandard;
import ch.fhnw.lems.business.SpaceCalculation;
import ch.fhnw.lems.controller.messages.MessageResultTransportCost;
import ch.fhnw.lems.entity.Cart;
import ch.fhnw.lems.entity.CustomerOrder;
import ch.fhnw.lems.entity.Shipping;
import ch.fhnw.lems.entity.User;
import ch.fhnw.lems.persistence.CartRepository;
import ch.fhnw.lems.persistence.OrderRepository;
import ch.fhnw.lems.persistence.ShippingRepository;
import ch.fhnw.lems.persistence.TransportCostRepository;
import ch.fhnw.lems.persistence.UserRepository;

//LUM
@RestController
public class TransportCostService {
	Logger logger = LoggerFactory.getLogger(TransportCostService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	TransportCostRepository transportCostRepository;
	
	@Autowired
	ShippingRepository shippingRepository;

	@GetMapping(path = "/api/transportCostCart", produces = " application/json")
	public MessageResultTransportCost getTransportCostCart(
			@RequestParam Long msgCartId,
			@RequestParam String msgShippingMethod,
			@RequestParam Integer msgAmountProduct1,
			@RequestParam Integer msgAmountProduct2,
			@RequestParam Integer msgAmountProduct3,
			@RequestParam Integer msgAmountProduct4) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);

		Cart cart = cartRepository.findById(msgCartId).get();
		MessageResultTransportCost msgResult = new MessageResultTransportCost();
		msgResult.setId(msgCartId);
		double distance = DistanceCalculation.calculateDistance(currentUser.getPostalCode());
		SpaceCalculation spaceCalculation = new SpaceCalculation();
		
		int amountProduct1 = msgAmountProduct1;
		int amountProduct2 = msgAmountProduct2;
		int amountProduct3 = msgAmountProduct3;
		int amountProduct4 = msgAmountProduct4;
		
		int pallett = spaceCalculation.totalPalletts(amountProduct1, amountProduct2, amountProduct3, amountProduct4);
		PriceCalculationStandard standardPrice = new PriceCalculationStandard();
		Double standardPriceOfPallett = standardPrice.calculateStandardPrice(transportCostRepository, distance, pallett);		
		msgResult.setTransportCostStandard(standardPriceOfPallett);
		
		PriceCalculationPackage pcp = new PriceCalculationPackage();
		msgResult.setDeliveryAvailable(pcp.packageOffer());
		msgResult.setPriceForDelivery(pcp.getPriceForDelivery());

		PriceCalculationExpress express = new PriceCalculationExpress();
		double expressPrice = express.calculateExpressPrice(distance, pallett);
		msgResult.setTransportCostExpress(expressPrice);
		msgResult.setDeliveryExpressAvailable(express.expressOffer(pallett));
		
		Shipping shipping = new Shipping();
		shipping.setShippingMethod(msgShippingMethod);
		shipping.setShippingPackageCost(pcp.getPriceForDelivery());
		shipping.setShippingStandardCost(standardPriceOfPallett);
		shipping.setShippingExpressCost(standardPriceOfPallett);		
		Shipping savedShipping = shippingRepository.save(shipping);
		cart.setShipping(savedShipping);
		msgResult.setSuccessful(true);
		return msgResult;
	}
		
	@GetMapping(path = "/api/transportCostOrder/{orderId}", produces = " application/json")
	public MessageResultTransportCost getTransportCostOrder(@PathVariable Long orderId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);

		List<CustomerOrder> order = orderRepository.findByUserId(currentUser.getUserId());

		MessageResultTransportCost msgResult = new MessageResultTransportCost();
		// TODO LUM show Transportcost of Order


		return msgResult;
	}
}
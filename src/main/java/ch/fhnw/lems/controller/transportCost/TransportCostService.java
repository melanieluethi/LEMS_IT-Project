package ch.fhnw.lems.controller.transportCost;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.lems.business.DistanceCalculation;
import ch.fhnw.lems.business.PriceCalculationExpress;
import ch.fhnw.lems.business.SpaceCalculation;
import ch.fhnw.lems.controller.messages.MessageResultTransportCost;
import ch.fhnw.lems.entity.Card;
import ch.fhnw.lems.entity.CustomerOrder;
import ch.fhnw.lems.entity.TransportCost;
import ch.fhnw.lems.entity.User;
import ch.fhnw.lems.persistence.CardRepository;
import ch.fhnw.lems.persistence.OrderRepository;
import ch.fhnw.lems.persistence.TransportCostRepository;
import ch.fhnw.lems.persistence.UserRepository;

//LUM
@RestController
public class TransportCostService {
	Logger logger = LoggerFactory.getLogger(TransportCostService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	CardRepository cardRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	TransportCostRepository transportCostRepository;

	@GetMapping(path = "/api/transportCostCard/{cardId}", produces = " application/json")
	public MessageResultTransportCost getTransportCostCard(@PathVariable Long cardId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);

		Card card = cardRepository.findByUser(currentUser.getUserId());
		
		MessageResultTransportCost msgResult = new MessageResultTransportCost();
		// TODO LUM/HIS getTransportCost

		double distance = DistanceCalculation.calculateDistance(currentUser.getPostalCode());
		SpaceCalculation spaceCalculation = new SpaceCalculation();
//		double pallett = spaceCalculation.totalPalletts();
		double pallett = 3.0d;

		Double transportId = Math.ceil(distance / 30);
		TransportCost tc = transportCostRepository.findById(transportId.longValue()).get();

		msgResult.setTransportCostStandard(123d);
		msgResult.setDeliveryAvailable(true);
		msgResult.setPriceForDelivery(120d);

		PriceCalculationExpress express = new PriceCalculationExpress();
		double expressPrice = express.calculateExpressPrice(distance, pallett);
		msgResult.setTransportCostExpress(expressPrice);

		msgResult.setDeliveryExpressAvailable(true);

		return msgResult;
	}

	@GetMapping(path = "/api/transportCostOrder/{orderId}", produces = " application/json")
	public MessageResultTransportCost getTransportCostOrder(@PathVariable Long orderId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);

		List<CustomerOrder> order = orderRepository.findByUserId(currentUser.getUserId());

		MessageResultTransportCost msgResult = new MessageResultTransportCost();
		// TODO LUM/HIS getTransportCost


		return msgResult;
	}
}
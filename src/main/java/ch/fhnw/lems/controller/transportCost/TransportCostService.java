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
import ch.fhnw.lems.business.PriceCalculationPackage;
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

//		Long cardId = cardRepository.findByUser(currentUser.getUserId());
		Card card = cardRepository.findById(cardId).get();
		
		MessageResultTransportCost msgResult = new MessageResultTransportCost();
		// TODO LUM/HIS getTransportCost

		double distance = DistanceCalculation.calculateDistance(currentUser.getPostalCode());
		SpaceCalculation spaceCalculation = new SpaceCalculation();
		
		int amountProduct1 = 1;
		int amountProduct2 = 5;
		int amountProduct3 = 2;
		int amountProduct4 = 8;
		
		int pallett = spaceCalculation.totalPalletts(amountProduct1, amountProduct2, amountProduct3, amountProduct4);

		Double transportId = Math.ceil(distance / 30);
		TransportCost tc = transportCostRepository.findById(transportId.longValue()).get();

		Double standardPriceOfPallett = getPallett(tc, pallett);		
		msgResult.setTransportCostStandard(standardPriceOfPallett);
		
		PriceCalculationPackage pcp = new PriceCalculationPackage();
		msgResult.setDeliveryAvailable(pcp.packageOffer());
		msgResult.setPriceForDelivery(pcp.getPriceForDelivery());

		PriceCalculationExpress express = new PriceCalculationExpress();
		double expressPrice = express.calculateExpressPrice(distance, pallett);
		msgResult.setTransportCostExpress(expressPrice);
		msgResult.setDeliveryExpressAvailable(express.expressOffer(pallett));

		return msgResult;
	}
	
	private Double getPallett(TransportCost tc, int pallett) {
		double pallet = 0.0d;
		switch (pallett) {
			case 1:
				pallet = tc.getPallet1();
				break;
			case 2: 
				pallet = tc.getPallet2();
				break;
			case 3:
				pallet = tc.getPallet3();
				break;
			case 4: 
				pallet = tc.getPallet4();
				break;
			case 5:
				pallet = tc.getPallet5();
				break;
			case 6: 
				pallet = tc.getPallet6();
				break;
			case 7:
				pallet = tc.getPallet7();
				break;
			case 8: 
				pallet = tc.getPallet8();
				break;
			case 9:
				pallet = tc.getPallet9();
				break;
			case 10: 
				pallet = tc.getPallet10();
				break;
			case 11:
				pallet = tc.getPallet11();
				break;
			case 12: 
				pallet = tc.getPallet12();
				break;
			default:
				break;
		}
		return pallet;
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
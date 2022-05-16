package ch.fhnw.lems.controller.transportCost;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.lems.controller.messages.MessageResultTransportCost;

//LUM
@RestController
public class TransportCostService {
	@GetMapping(path = "/api/transportCost", produces = " application/json")
	public MessageResultTransportCost getTransportCost(@PathVariable Long userId) {
		MessageResultTransportCost msgResult = new MessageResultTransportCost();

		// TODO LUM/HIS getTransportCost

		return msgResult;
	}
}
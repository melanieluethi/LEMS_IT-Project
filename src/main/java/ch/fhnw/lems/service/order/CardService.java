package ch.fhnw.lems.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.lems.persistence.CardRepository;

// LUM
@RestController
public class CardService {
	@Autowired
	private CardRepository cardRepository;
}
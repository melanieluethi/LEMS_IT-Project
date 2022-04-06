package ch.fhnw.lems.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.lems.persistence.ProductRepository;

//LUM
@RestController
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
}
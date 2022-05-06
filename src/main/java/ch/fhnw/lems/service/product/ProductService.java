package ch.fhnw.lems.service.product;

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

import ch.fhnw.lems.entity.Product;
import ch.fhnw.lems.entity.User;
import ch.fhnw.lems.entity.UserRole;
import ch.fhnw.lems.persistence.ProductRepository;
import ch.fhnw.lems.persistence.UserRepository;
import ch.fhnw.lems.service.messages.MessageAddProduct;
import ch.fhnw.lems.service.messages.MessageChangeProduct;
import ch.fhnw.lems.service.messages.MessageResultProduct;

//LUM
@RestController
public class ProductService {
	Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(path= "/api/products", produces = " application/json")
	public ArrayList<MessageResultProduct> getProducts(){
		List<Product> products = productRepository.findAll();
		
		ArrayList<MessageResultProduct> results = new ArrayList<>();
		products.forEach(p -> {
			MessageResultProduct msgProduct = new MessageResultProduct();
			msgProduct.setId(p.getProductId());
			msgProduct.setDescription(p.getDescription());
			msgProduct.setDiscount(p.getDiscount());
			msgProduct.setPrice(p.getPrice());
			msgProduct.setProductImg(p.getProductImg());
			results.add(msgProduct);
			logger.info("Get product: " + p.getProductName());
		});
		return results;
	}
	
	@GetMapping(path= "/api/product/{productId}", produces = " application/json")
	public MessageResultProduct getProduct(@PathVariable Long productId){
		Product product = productRepository.findById(productId).get();		
		MessageResultProduct msgResult = new MessageResultProduct();
		if (product != null) {
			logger.info("Get product: " + product.getProductName());
			msgResult.setSuccessful(true);
			msgResult.setId(product.getProductId());
			msgResult.setDescription(product.getDescription());
			msgResult.setDiscount(product.getDiscount());
			msgResult.setPrice(product.getPrice());
			msgResult.setProductImg(product.getProductImg());
		} else {
			logger.info("Get product was not successful.");
			msgResult.setSuccessful(true);
		}
		return msgResult;
	}
	
	@PostMapping (path = "/api/createProduct", produces = "application/json")
	public boolean createProduct(@RequestBody MessageAddProduct msgProduct) {
		Product product = new Product();
		product.setDescription(msgProduct.getDescription());
		product.setDiscount(msgProduct.getDiscount());
		product.setPrice(msgProduct.getPrice());
		product.setProductImg(msgProduct.getProductImg());
		productRepository.save(product);

		logger.info("Create product: " + product.getProductName());
		return true;
	}
	
	@PutMapping(path = "/api/changeProduct", produces = "application/json")
	public boolean changeProduct(@RequestBody MessageChangeProduct msgProduct) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);
		
		if(currentUser.getRole().getRole().equals(UserRole.ADMIN)) {
			Product product = new Product();
			product.setDescription(msgProduct.getDescription());
			product.setDiscount(msgProduct.getDiscount());
			product.setPrice(msgProduct.getPrice());
			product.setProductImg(msgProduct.getProductImg());
			productRepository.save(product);

			logger.info("Change product " + product.getProductName() + " was succesful.");
			return true;
		} else {
			logger.info("Change product was not succesful.");
			return false;
		}
	}
}
package ch.fhnw.lems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// LUM
@Controller
public class ViewsController {
	@GetMapping("/adminOrdersView")
	public String adminOrdersView() {
		return "adminOrdersView";
	}

	@GetMapping("/adminProductsView")
	public String adminProductsView() {
		return "adminProductsView";
	}

	@GetMapping("/adminUsersView")
	public String adminUsersView() {
		return "adminUsersView";
	}

	@GetMapping("/adminView")
	public String adminView() {
		return "adminView";
	}

	@GetMapping("/cartView")
	public String cartView() {
		return "cartView";
	}

	@GetMapping("/helpView")
	public String helpView() {
		return "helpView";
	}

	@GetMapping("/homeView")
	public String homeView() {
		return "homeView";
	}

	@GetMapping("/loginView")
	public String loginView() {
		return "LoginView.html";
	}

	@GetMapping("/logoutView")
	public String logoutView() {
		return "logoutView";
	}

	@GetMapping("/productChoiceView")
	public String productChoiceView() {
		return "productChoiceView";
	}

	@GetMapping("/registerView")
	public String registerView() {
		return "registerView";
	}
	
	@GetMapping("/successfulOrderView")
	public String successfulOrderView() {
		return "successfulOrderView";
	}

	@GetMapping("/userManagementView")
	public String userManagementView() {
		return "userManagementView";
	}
}
package ch.fhnw.lems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// LUM
@Controller
public class ViewsController {
	@GetMapping("/adminOrdersView")
	public String adminOrdersView() {
		return "AdminOrdersView.html";
	}

	@GetMapping("/adminProductsView")
	public String adminProductsView() {
		return "AdminProductsView.html";
	}

	@GetMapping("/adminUsersView")
	public String adminUsersView() {
		return "AdminUsersView.html";
	}

	@GetMapping("/adminView")
	public String adminView() {
		return "AdminView.html";
	}

	@GetMapping("/cartView")
	public String cartView() {
		return "CartView.html";
	}

	@GetMapping("/helpView")
	public String helpView() {
		return "HelpView.html";
	}

	@GetMapping("/homeView")
	public String homeView() {
		return "HomeView.html";
	}

	@GetMapping("/loginView")
	public String loginView() {
		return "LoginView.html";
	}

	@GetMapping("/logoutView")
	public String logoutView() {
		return "LogoutView.html";
	}

	@GetMapping("/productChoiceView")
	public String productChoiceView() {
		return "ProductChoiceView.html";
	}

	@GetMapping("/registerView")
	public String registerView() {
		return "RegisterView.html";
	}
	
	@GetMapping("/successfulOrderView")
	public String successfulOrderView() {
		return "SuccessfulOrderView.html";
	}

	@GetMapping("/userManagementView")
	public String userManagementView() {
		return "UserManagementView.html";
	}
}
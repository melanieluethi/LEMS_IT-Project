package ch.fhnw.lems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// LUM
@Controller
public class ViewsController {
	@GetMapping("/adminOrders")
	public String adminOrders() {
		return "AdminOrdersView.html";
	}

	@GetMapping("/adminProducts")
	public String adminProducts() {
		return "AdminProductsView.html";
	}

	@GetMapping("/adminUsers")
	public String adminUsers() {
		return "AdminUsersView.html";
	}

	@GetMapping("/adminProductDetail")
	public String adminProductDetail() {
		return "AdminProductDetailView.html";
	}
	
	@GetMapping("/adminOrderDetail")
	public String adminOrderDetail() {
		return "AdminOrderDetailView.html";
	}
	
	@GetMapping("/adminUserManagement")
	public String adminUserManagement() {
		return "AdminUserManagementView.html";
	}
	
	@GetMapping("/adminHelp")
	public String adminHelp() {
		return "AdminHelpView.html";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "AdminView.html";
	}

	@GetMapping("/cart")
	public String cart() {
		return "CartView.html";
	}

	@GetMapping("/help")
	public String help() {
		return "HelpView.html";
	}

	@GetMapping("/home")
	public String home() {
		return "HomeView.html";
	}

	@GetMapping("/login")
	public String login() {
		return "LoginView.html";
	}
	
	@GetMapping("/logoutView")
	public String logout() {
		return "LogoutView.html";
	}

	@GetMapping("/productChoice")
	public String productChoice() {
		return "ProductChoiceView.html";
	}

	@GetMapping("/register")
	public String register() {
		return "RegisterView.html";
	}
	
	@GetMapping("/successfulOrder")
	public String successfulOrderView() {
		return "SuccessfulOrderView.html";
	}

	@GetMapping("/userManagement")
	public String userManagementView() {
		return "UserManagementView.html";
	}
	
	@GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }
}
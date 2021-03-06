package ch.fhnw.lems.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import ch.fhnw.lems.entity.UserRole;

// LUM
@RestController
public class HomeRessourceController {
	Logger logger = LoggerFactory.getLogger(HomeRessourceController.class);
		
	@GetMapping("/")
	public RedirectView homePage() {
		// Übernommen von Spring 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().toString();

		logger.info("Show correct Page");
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			if (role.contains(UserRole.ADMIN.name())) {
				logger.info("Show Admin Page");
				return new RedirectView("/admin");
			} else if (role.contains(UserRole.USER.name())) {
				logger.info("Show Home Page");
				return new RedirectView("/home");
			}
		}
		logger.info("User is not logged in. That is why Login Page is shown.");
		return new RedirectView("/login");
	}
	
	//https://stackoverflow.com/questions/20848312/how-to-correctly-logout-user-in-spring-security
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	        logger.info("Logout was successful.");
	    }
	    return "redirect:/logoutView";
	}
}
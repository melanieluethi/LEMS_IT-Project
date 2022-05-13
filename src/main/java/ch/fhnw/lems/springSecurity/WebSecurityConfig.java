package ch.fhnw.lems.springSecurity;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import ch.fhnw.lems.entity.LoginHistory;
import ch.fhnw.lems.entity.User;
import ch.fhnw.lems.entity.UserRole;
import ch.fhnw.lems.persistence.LoginHistoryRepository;
import ch.fhnw.lems.persistence.UserRepository;

// LUM
/* 
 * https://www.baeldung.com/spring-security-login
 * https://spring.io/guides/gs/securing-web/
 * https://www.codejava.net/frameworks/spring-boot/spring-security-authentication-success-handler-examples
*/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginHistoryRepository loginHistoryRepository;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/**").permitAll()
				.antMatchers("/admin").hasRole(UserRole.ADMIN.name())
				.antMatchers("/user").hasRole(UserRole.USER.name())
				// rights for services
				.antMatchers("/api/user").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/api/createUser").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/api/profileSettings/{userId}").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/api/userProfileSettings").hasRole(UserRole.ADMIN.name())
				.antMatchers("/api/profileSettings").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/api/changePassword").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/api/products").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/api/product/{productId}").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/api/addProductToCard").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/api/shoppingCard/{userId}").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/api/product").hasRole(UserRole.ADMIN.name())
				.antMatchers("/api/changeProduct").hasRole(UserRole.ADMIN.name())
				.antMatchers("/api/order/").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/api/order/{orderd}").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/api/orders/{userId}").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/api/orders").hasRole(UserRole.ADMIN.name())
				.antMatchers("/api/order").hasRole(UserRole.ADMIN.name())
				.antMatchers("/api/changeLanguage").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/api/language").permitAll()
				// Rights for views
				.antMatchers("/adminOrders").hasAnyRole(UserRole.ADMIN.name())
				.antMatchers("/adminProducts").hasAnyRole(UserRole.ADMIN.name())
				.antMatchers("/adminUsers").hasAnyRole(UserRole.ADMIN.name())
				.antMatchers("/admin").hasAnyRole(UserRole.ADMIN.name())
				.antMatchers("/cart").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/help").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/home").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/logout").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/productChoice").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/successfulOrder").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.antMatchers("/userManagement").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
				.anyRequest().authenticated().and().formLogin()
				.loginPage("/login").successHandler(new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						// Own logic after login
						UserDetails userDetails = (UserDetails) authentication.getPrincipal();
						String username = userDetails.getUsername();
						User user = (User) userRepository.findByUsername(username);
						logger.info("User: " + username + " has been successfully logged in.");
						Timestamp timestamp = new Timestamp(System.currentTimeMillis());
						LoginHistory loginHistory = new LoginHistory();
						loginHistory.setLogin(timestamp);
						loginHistory.setUser(user);
						loginHistoryRepository.save(loginHistory);
						if (user.getRole().getRole().equals(UserRole.ADMIN)) {
							response.sendRedirect("/admin");
						} else {
							response.sendRedirect("/home");
						}
					}
				}).and().logout().logoutSuccessHandler(new LogoutSuccessHandler() {
					@Override
					public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						// Own logic after logout
						UserDetails userDetails = (UserDetails) authentication.getPrincipal();
						String username = userDetails.getUsername();
						User user = (User) userRepository.findByUsername(username);
						Long lastLoginHistoryId = loginHistoryRepository.lastUserLogin(user.getUserId());
						LoginHistory history = loginHistoryRepository.findById(lastLoginHistoryId).get();
						Timestamp timestamp = new Timestamp(System.currentTimeMillis());
						history.setLogout(timestamp);
						loginHistoryRepository.save(history);
						logger.info("User: " + username + " has been successfully logget out.");
						response.sendRedirect("/login");
					}
				}).permitAll();
		http.csrf().disable();
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
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

	private final static String USER_ROLE_ADMIN = UserRole.ADMIN.name();;
	private final static String USER_ROLE_USER = UserRole.USER.name();
	
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
				// rights for services
				.antMatchers("/api/user").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/api/createUser").permitAll()
				.antMatchers("/api/profileSettings/{userId}").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/api/userProfileSettings").hasRole(USER_ROLE_ADMIN)
				.antMatchers("/api/profileSettings").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/api/changePassword").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/api/products").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/api/product/{productId}").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/api/addProductToCard").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/api/shoppingCard/{userId}").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/api/product").hasRole(USER_ROLE_ADMIN)
				.antMatchers("/api/changeProduct").hasRole(USER_ROLE_ADMIN)
				.antMatchers("/api/order/").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/api/order/{orderd}").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/api/orders/{userId}").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/api/orders").hasRole(USER_ROLE_ADMIN)
				.antMatchers("/api/order").hasRole(USER_ROLE_ADMIN)
				.antMatchers("/api/changeLanguage").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/api/language").permitAll()
				// Rights for views
				.antMatchers("/adminOrders").hasRole(USER_ROLE_ADMIN)
				.antMatchers("/adminProducts").hasRole(USER_ROLE_ADMIN)
				.antMatchers("/adminUsers").hasRole(USER_ROLE_ADMIN)
				.antMatchers("/admin").hasRole(USER_ROLE_ADMIN)
				.antMatchers("/user").hasRole(USER_ROLE_USER)				
				.antMatchers("/cart").hasRole(USER_ROLE_USER)
				.antMatchers("/help").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/home").hasRole(USER_ROLE_USER)
				.antMatchers("/productChoice").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/successfulOrder").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/userManagement").hasAnyRole(USER_ROLE_ADMIN, USER_ROLE_USER)
				.antMatchers("/**").permitAll()
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
				}).and().logout().deleteCookies("JSESSIONID").logoutSuccessHandler(new LogoutSuccessHandler() {
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
						response.sendRedirect("/logoutView");
					}
				}).permitAll();
		http.csrf().disable();
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
package ch.fhnw.lems.controller.profile;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.lems.controller.messages.MessageChangeLanguage;
import ch.fhnw.lems.controller.messages.MessageChangePassword;
import ch.fhnw.lems.controller.messages.MessageChangeProfileSetting;
import ch.fhnw.lems.controller.messages.MessageResultLanguage;
import ch.fhnw.lems.controller.messages.MessageResultProfileSetting;
import ch.fhnw.lems.controller.messages.MessageResultStandard;
import ch.fhnw.lems.controller.messages.MessageUser;
import ch.fhnw.lems.entity.Country;
import ch.fhnw.lems.entity.Language;
import ch.fhnw.lems.entity.Role;
import ch.fhnw.lems.entity.User;
import ch.fhnw.lems.entity.UserRole;
import ch.fhnw.lems.persistence.RoleRepository;
import ch.fhnw.lems.persistence.UserRepository;

//LUM
@RestController
public class ProfileService {
	Logger logger = LoggerFactory.getLogger(ProfileService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@PostMapping(path = "/api/createUser", produces = "application/json")
	public MessageResultStandard createUser(@RequestBody MessageUser msgUser) {
		User user = new User();
		user.setFirstname(msgUser.getFirstname());
		user.setLastname(msgUser.getLastname());
		user.setEmail(msgUser.getEmail());
		user.setUsername(msgUser.getUsername());
		user.setPassword(BCrypt.hashpw(msgUser.getPassword(), BCrypt.gensalt(12)));
		user.setLanguage(Language.valueOf(msgUser.getLanguage().toUpperCase()));
		user.setAddress(msgUser.getAddress());
		user.setPostalCode(msgUser.getPostalCode());
		user.setCity(msgUser.getCity());
		user.setCountry(Country.valueOf(msgUser.getCountry().toUpperCase()));
		Role role = roleRepository.findByRole(UserRole.USER);
		user.setRole(role);
		User savedUser = userRepository.save(user);

		MessageResultStandard msgResult = new MessageResultStandard();
		if (savedUser != null) {
			logger.info("Create User " + savedUser.getUsername() + " was successful.");
			msgResult.setSuccessful(true);
		} else {
			logger.info("Create User was not successful.");
			msgResult.setSuccessful(false);
		}
		return msgResult;
	}

	@GetMapping(path = "/api/profileSettings/{userId}", produces = " application/json")
	public MessageResultProfileSetting getUser(@PathVariable Long userId) {
		User user = userRepository.findById(userId).get();
		MessageResultProfileSetting msgResult = new MessageResultProfileSetting();
		if (user != null) {
			msgResult.setSuccessful(true);
			msgResult.setId(user.getUserId());
			msgResult.setFirstname(user.getFirstname());
			msgResult.setLastname(user.getLastname());
			msgResult.setEmail(user.getEmail());
			msgResult.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
			msgResult.setRoleId(user.getRole().getRoleId());
			msgResult.setAddress(user.getAddress());
			msgResult.setPostalCode(user.getPostalCode());
			msgResult.setCity(user.getCity());
			msgResult.setCountry(user.getCountry().name());			
			logger.info("Get User " + user.getUsername() + " was successful.");
		} else {
			logger.info("User do not exist.");
			msgResult.setSuccessful(false);
		}
		return msgResult;
	}

	@GetMapping(path = "/api/userProfileSettings", produces = "application/json")
	public ArrayList<MessageResultProfileSetting> getUsers() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);

		ArrayList<MessageResultProfileSetting> msgResults = new ArrayList<>();
		if (currentUser.getRole().getRole().equals(UserRole.ADMIN)) {
			List<User> users = userRepository.findAll();
			users.forEach(u -> {
				MessageResultProfileSetting msgResultUser = new MessageResultProfileSetting();
				msgResultUser.setId(u.getUserId());
				msgResultUser.setUsername(u.getUsername());
				msgResultUser.setFirstname(u.getFirstname());
				msgResultUser.setLastname(u.getLastname());
				msgResultUser.setEmail(u.getEmail());
				msgResultUser.setPassword(BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(12)));
				msgResultUser.setAddress(u.getAddress());
				msgResultUser.setPostalCode(u.getPostalCode());
				msgResultUser.setCity(u.getCity());
				msgResultUser.setCountry(u.getCountry().name());	
				msgResultUser.setRoleId(u.getRole().getRoleId());
				msgResultUser.setLanguage(u.getLanguage().name());
				msgResults.add(msgResultUser);
				logger.info("Get User " + u.getUsername() + " was successful.");
			});
		}
		return msgResults;
	}

	@GetMapping(path = "/api/user", produces = "application/json")
	public User getUser(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		String username = userDetails.getUsername();
		User user = (User) userRepository.findByUsername(username);
		logger.info("Get User " + user.getUsername() + " was successful.");
		return user;
	}

	@PutMapping(path = "/api/profileSettings", produces = "application/json")
	public boolean changeProfileSettings(@RequestBody MessageChangeProfileSetting msgProfileSetting) {
		User user = userRepository.findById(msgProfileSetting.getUserId()).get();
		if (user != null) {
			user.setFirstname(msgProfileSetting.getFirstname());
			user.setLastname(msgProfileSetting.getLastname());
			user.setEmail(msgProfileSetting.getEmail());
			user.setPassword(BCrypt.hashpw(msgProfileSetting.getPassword(), BCrypt.gensalt(12)));
			user.setLanguage(Language.valueOf(msgProfileSetting.getLanguage()));
			user.setAddress(msgProfileSetting.getAddress());
			user.setPostalCode(msgProfileSetting.getPostalCode());
			user.setCity(msgProfileSetting.getCity());
			user.setCountry(Country.valueOf(msgProfileSetting.getCountry().toUpperCase()));
			Role role = roleRepository.findById(msgProfileSetting.getRoleId()).get();
			user.setRole(role);
			userRepository.save(user);
			logger.info("Change User Settings of" + user.getUsername() + " were successful.");
			return true;
		} else {
			logger.info("Change User Settings were not successful.");
			return false;
		}
	}

	@PutMapping(path = "/api/changePassword", produces = "application/json")
	public boolean changePassword(@RequestBody MessageChangePassword msgPassword) {
		User user = userRepository.findById(msgPassword.getUserId()).get();
		if (user != null && !(msgPassword.getNewPassword().equals(msgPassword.getOldPassword())
				&& msgPassword.getNewPassword().length() >= 8)) {
			user.setPassword(BCrypt.hashpw(msgPassword.getNewPassword(), BCrypt.gensalt(12)));
			userRepository.save(user);

			logger.info("Change Password of" + user.getUsername() + " was successful.");
			return true;
		} else {
			logger.info("Change Password was not successful.");
			return false;
		}
	}

	@GetMapping(path = "/api/language", produces = "application/json")
	public MessageResultLanguage getLanguage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);
		MessageResultLanguage msgResult = new MessageResultLanguage();
		if (currentUser != null) {
			msgResult.setSuccessful(true);
			msgResult.setLanguage(currentUser.getLanguage().name().toLowerCase());
			logger.info("Get Language of" + currentUser.getUsername() + " was successful.");
		} else {
			msgResult.setSuccessful(false);
			logger.info("Get Language was not successful. User is not logged in or not exists.");
		}
		return msgResult;
	}
	
	@PutMapping(path = "/api/changeLanguage", produces = "application/json")
	public boolean changeLanguage(@RequestBody MessageChangeLanguage msgLanguage) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);
		if (currentUser != null) {
			currentUser.setLanguage(Language.valueOf(msgLanguage.getLanguage().toUpperCase()));
			userRepository.save(currentUser);
			logger.info("Change Language of" + currentUser.getUsername() + " was successful.");
			return true;
		} else {
			logger.info("Change Language was not successful.");
			return false;
		}
	}
}
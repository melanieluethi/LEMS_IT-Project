package ch.fhnw.lems.service.profile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.lems.entity.Language;
import ch.fhnw.lems.entity.Role;
import ch.fhnw.lems.entity.User;
import ch.fhnw.lems.entity.UserRole;
import ch.fhnw.lems.persistence.RoleRepository;
import ch.fhnw.lems.persistence.UserRepository;
import ch.fhnw.lems.service.messages.MessageChangeLanguage;
import ch.fhnw.lems.service.messages.MessageChangePassword;
import ch.fhnw.lems.service.messages.MessageChangeProfileSetting;
import ch.fhnw.lems.service.messages.MessageCreateUser;
import ch.fhnw.lems.service.messages.MessageResultProfileSetting;
import ch.fhnw.lems.service.messages.MessageResultStandard;

//LUM
@RestController
public class ProfileService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostMapping (path = "/api/createUser", produces = "application/json")
	public MessageResultStandard createUser(@RequestBody MessageCreateUser msgUser) {
		User user = new User();
		user.setFirstname(msgUser.getFirstname());
		user.setLastname(msgUser.getLastname());
		user.setEmail(msgUser.getEmail());
		user.setPassword(BCrypt.hashpw(msgUser.getPassword(), BCrypt.gensalt(12)));
		user.setLanguage(Language.valueOf(msgUser.getLanguage()));
		Role role = roleRepository.findByRole(UserRole.USER);
		user.setRole(role);
		User savedUser = userRepository.save(user);
		
		MessageResultStandard msgResult = new MessageResultStandard();
		if(savedUser != null) {
			msgResult.setSuccessful(true);
		} else {
			msgResult.setSuccessful(false);
		}
		return msgResult;
	}
	
	@GetMapping(path= "/api/profileSettings/{userId}", produces = " application/json")
	public MessageResultProfileSetting getUser(@PathVariable Long userId){
		User user = userRepository.getById(userId);		
		MessageResultProfileSetting msgResult = new MessageResultProfileSetting();
		if(user != null) {
			msgResult.setSuccessful(true);
			msgResult.setId(user.getUserId());
			msgResult.setFirstname(user.getFirstname());
			msgResult.setLastname(user.getLastname());
			msgResult.setEmail(user.getEmail());
			msgResult.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));			
			msgResult.setRoleId(user.getRole().getRoleId());
		} else {
			msgResult.setSuccessful(false);			
		}
		return msgResult;
	}
	
	@GetMapping(path= "/api/userProfileSettings", produces = " application/json")
	public ArrayList<MessageResultProfileSetting> getUsers(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);
		
		ArrayList<MessageResultProfileSetting> msgResults = new ArrayList<>();
		if(currentUser.getRole().getRole().equals(UserRole.ADMIN)) {
			List<User> users = userRepository.findAll();
			users.forEach(u -> {
				MessageResultProfileSetting msgResultUser = new MessageResultProfileSetting();
				msgResultUser.setId(u.getUserId());
				msgResultUser.setFirstname(u.getFirstname());
				msgResultUser.setLastname(u.getLastname());
				msgResultUser.setEmail(u.getEmail());
				msgResultUser.setPassword(BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(12)));			
				msgResultUser.setRoleId(u.getRole().getRoleId());
				msgResults.add(msgResultUser);
			});	
		} 
		return msgResults;
	}
	
	@PutMapping(path = "/api/profileSettings", produces = "application/json")
	public boolean changeProfileSettings(@RequestBody MessageChangeProfileSetting msgProfileSetting) {
		User user = userRepository.getById(msgProfileSetting.getUserId());
		if(user != null) {
			user.setFirstname(msgProfileSetting.getFirstname());
			user.setLastname(msgProfileSetting.getLastname());
			user.setEmail(msgProfileSetting.getEmail());
			user.setPassword(BCrypt.hashpw(msgProfileSetting.getPassword(), BCrypt.gensalt(12)));
			user.setLanguage(Language.valueOf(msgProfileSetting.getLanguage()));
			Role role = roleRepository.getById(msgProfileSetting.getRoleId());
			user.setRole(role);
			userRepository.save(user);
			return true;
		} else {
			return false;
		}
	}
	
	@PutMapping(path = "/api/changePassword", produces = "application/json")
	public boolean changePassword(@RequestBody MessageChangePassword msgPassword) {
		// TODO LUM validate Password
		User user = userRepository.getById(msgPassword.getUserId());
		if(user != null) {
			user.setPassword(BCrypt.hashpw(msgPassword.getNewPassword(), BCrypt.gensalt(12)));
			userRepository.save(user);
			return true;
		} else {
			return false;
		}
	}
	
	@PutMapping(path = "/api/changeLanguage", produces = "application/json")
	public boolean changeLanguage(@RequestBody MessageChangeLanguage msgLanguage) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currentUser = userRepository.findByUsername(username);
		
		if(currentUser != null) {
			currentUser.setLanguage(Language.valueOf(msgLanguage.getLanguage()));
			userRepository.save(currentUser);
			return true;
		} else {
			return false;
		}
	}	
}
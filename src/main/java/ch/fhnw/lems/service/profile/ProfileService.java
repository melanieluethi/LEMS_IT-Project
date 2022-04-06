package ch.fhnw.lems.service.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.lems.dto.Role;
import ch.fhnw.lems.dto.User;
import ch.fhnw.lems.dto.UserRole;
import ch.fhnw.lems.persistence.RoleRepository;
import ch.fhnw.lems.persistence.UserRepository;
import ch.fhnw.lems.service.messages.MessageChangePassword;
import ch.fhnw.lems.service.messages.MessageChangeProfileSetting;
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
	public MessageResultStandard createUser(@RequestBody User msgUser) {
		User user = new User();
		user.setFirstname(msgUser.getFirstname());
		user.setLastname(msgUser.getLastname());
		user.setEmail(msgUser.getEmail());
		user.setPassword(BCrypt.hashpw(msgUser.getPassword(), BCrypt.gensalt(12)));
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
		Optional<User> user = userRepository.findById(userId);		
		MessageResultProfileSetting msgResult = new MessageResultProfileSetting();
		if(user.isPresent()) {
			msgResult.setSuccessful(true);
			msgResult.setId(user.get().getUserId());
			msgResult.setFirstname(user.get().getFirstname());
			msgResult.setLastname(user.get().getLastname());
			msgResult.setEmail(user.get().getEmail());
			msgResult.setPassword(BCrypt.hashpw(user.get().getPassword(), BCrypt.gensalt(12)));			
			msgResult.setRoleId(user.get().getRole().getRoleId());
		} else {
			msgResult.setSuccessful(false);			
		}
		return msgResult;
	}
	
	@GetMapping(path= "/api/userProfileSettings", produces = " application/json")
	public ArrayList<MessageResultProfileSetting> getUsers(){
		// TODO LUM Needs to check that only the admin can do it
		List<User> users = userRepository.findAll();
		
		ArrayList<MessageResultProfileSetting> msgResults = new ArrayList<>();
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
		return msgResults;
	}
	
	@PutMapping(path = "/api/profileSettings", produces = "application/json")
	public boolean changeProfileSettings(@RequestBody MessageChangeProfileSetting msgProfileSetting) {
		Optional<User> user = userRepository.findById(msgProfileSetting.getUserId());
		if(user.isPresent()) {
			User u = user.get();
			u.setFirstname(msgProfileSetting.getFirstname());
			u.setLastname(msgProfileSetting.getLastname());
			u.setEmail(msgProfileSetting.getEmail());
			u.setPassword(BCrypt.hashpw(msgProfileSetting.getPassword(), BCrypt.gensalt(12)));
			Optional<Role> role = roleRepository.findById(msgProfileSetting.getRoleId());
			u.setRole(role.get());
			userRepository.save(u);
			return true;
		} else {
			return false;
		}
	}
	
	@PutMapping(path = "/api/changePassword", produces = "application/json")
	public boolean changePassword(@RequestBody MessageChangePassword msgPassword) {
		// TODO LUM validate Password
		Optional<User> user = userRepository.findById(msgPassword.getUserId());
		if(user.isPresent()) {
			User u = user.get();
			u.setPassword(BCrypt.hashpw(msgPassword.getNewPassword(), BCrypt.gensalt(12)));
			userRepository.save(u);
			return true;
		} else {
			return false;
		}
	}	
}
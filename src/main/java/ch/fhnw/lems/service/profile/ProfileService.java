package ch.fhnw.lems.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.lems.dto.Role;
import ch.fhnw.lems.dto.User;
import ch.fhnw.lems.dto.UserRole;
import ch.fhnw.lems.persistence.RoleRepository;
import ch.fhnw.lems.persistence.UserRepository;

//LUM
@RestController
public class ProfileService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostMapping (path = "/api/createUser", produces = "application/json")
	public User createUser(@RequestBody User msgUser) {
		User user = new User();
		user.setFirstname(msgUser.getFirstname());
		user.setLastname(msgUser.getLastname());
		user.setEmail(msgUser.getEmail());
		user.setPassword(BCrypt.hashpw(msgUser.getPassword(), BCrypt.gensalt(12)));
		Role role = roleRepository.findByRole(UserRole.USER);
		user.setRole(role);
		userRepository.save(user);
		return user;
	}
}
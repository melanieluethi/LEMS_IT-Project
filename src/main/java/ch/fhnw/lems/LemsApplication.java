package ch.fhnw.lems;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.fhnw.lems.dto.Role;
import ch.fhnw.lems.dto.User;
import ch.fhnw.lems.dto.UserRole;
import ch.fhnw.lems.persistence.RoleRepository;
import ch.fhnw.lems.persistence.UserRepository;

// LUM
@SpringBootApplication
public class LemsApplication {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(LemsApplication.class, args);
	}

	@PostConstruct
	public void createRole() {
		Role adminRole = new Role();
		adminRole.setRole(UserRole.ADMIN);
		Role userRole = new Role();
		userRole.setRole(UserRole.USER);
		roleRepository.saveAll(Arrays.asList(adminRole, userRole));
	}
	
	// Vordefinierter Admin wird erstellt.
	@PostConstruct
	public void creatAdmin() {
		if (userRepository.findAll().isEmpty()) {
			User admin = new User();
			admin.setFirstname("LEMS");
			admin.setLastname("Administrator");
			admin.setEmail("administrator@lems.ch");
			admin.setPassword("IAmAAdminOfLems2022");	
			Role role = roleRepository.findByRole(UserRole.ADMIN);
			admin.setRole(role);
			userRepository.save(admin);			
		}
	}
}
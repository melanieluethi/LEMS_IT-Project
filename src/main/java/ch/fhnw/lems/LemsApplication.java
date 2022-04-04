package ch.fhnw.lems;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.fhnw.lems.dto.Role;
import ch.fhnw.lems.dto.TransportCost;
import ch.fhnw.lems.dto.User;
import ch.fhnw.lems.dto.UserRole;
import ch.fhnw.lems.persistence.RoleRepository;
import ch.fhnw.lems.persistence.TransportCostRepository;
import ch.fhnw.lems.persistence.UserRepository;

// LUM
@SpringBootApplication
public class LemsApplication {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private TransportCostRepository transportCostRepository;

	public static void main(String[] args) {
		SpringApplication.run(LemsApplication.class, args);
	}

	@PostConstruct
	public void createRole() {
		if (roleRepository.findAll().isEmpty()) {
			Role adminRole = new Role();
			adminRole.setRole(UserRole.ADMIN);
			Role userRole = new Role();
			userRole.setRole(UserRole.USER);
			roleRepository.saveAll(Arrays.asList(adminRole, userRole));
		}
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

	// method for HiS Claculation Stuff
	@PostConstruct
	public void createTransportCost() {
		if (transportCostRepository.findAll().isEmpty()) {
			TransportCost transportCost1 = new TransportCost();
			transportCost1.setTransportcostId(30l);
			transportCost1.setPallet1(58.65);
			// continue Pal2-12

			TransportCost transportCost2 = new TransportCost();
			transportCost2.setTransportcostId(60l);
			transportCost2.setPallet1(00.00);
			// continue Pal2-12
			
			// continue Distance 90, 120, ...
			
			transportCostRepository.saveAll(Arrays.asList(transportCost1, transportCost2, transportCost3,
					transportCost4, transportCost5, transportCost6, transportCost7, transportCost8, transportCost9,
					transportCost10, transportCost11, transportCost12));
		}
	}

}
package ch.fhnw.lems;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.fhnw.lems.entity.Language;
import ch.fhnw.lems.entity.Role;
import ch.fhnw.lems.entity.TransportCost;
import ch.fhnw.lems.entity.User;
import ch.fhnw.lems.entity.UserRole;
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
			admin.setUsername("admin");
			admin.setFirstname("LEMS");
			admin.setLastname("Administrator");
			admin.setEmail("administrator@lems.ch");
			admin.setPassword("IAmAAdminOfLems2022");
			admin.setLanguage(Language.GERMAN);
			Role role = roleRepository.findByRole(UserRole.ADMIN);
			admin.setRole(role);
			userRepository.save(admin);
		}
	}

	// method for HiS (Claculation Stuff)
	@PostConstruct
	public void createTransportCost() {
		if (transportCostRepository.findAll().isEmpty()) {
			TransportCost transportCost1 = new TransportCost();
			transportCost1.setTransportcostId(30l);
			transportCost1.setPallet1(58.65);
			transportCost1.setPallet2(87.00);
			transportCost1.setPallet3(114.50);
			transportCost1.setPallet4(137.10);
			transportCost1.setPallet5(160.80);
			transportCost1.setPallet6(181.65);
			transportCost1.setPallet7(201.45);
			transportCost1.setPallet8(220.30);
			transportCost1.setPallet9(238.35);
			transportCost1.setPallet10(255.35);
			transportCost1.setPallet11(271.45);
			transportCost1.setPallet12(286.55);

			TransportCost transportCost2 = new TransportCost();
			transportCost2.setTransportcostId(60l);
			transportCost2.setPallet1(67.00);
			transportCost2.setPallet2(99.40);
			transportCost2.setPallet3(130.85);
			transportCost2.setPallet4(156.75);
			transportCost2.setPallet5(183.80);
			transportCost2.setPallet6(207.55);
			transportCost2.setPallet7(230.25);
			transportCost2.setPallet8(251.85);
			transportCost2.setPallet9(272.40);
			transportCost2.setPallet10(291.90);
			transportCost2.setPallet11(310.25);
			transportCost2.setPallet12(327.50);

			TransportCost transportCost3 = new TransportCost();
			transportCost3.setTransportcostId(90l);
			transportCost3.setPallet1(75.40);
			transportCost3.setPallet2(111.90);
			transportCost3.setPallet3(147.15);
			transportCost3.setPallet4(176.35);
			transportCost3.setPallet5(206.65);
			transportCost3.setPallet6(233.50);
			transportCost3.setPallet7(259.00);
			transportCost3.setPallet8(283.35);
			transportCost3.setPallet9(306.45);
			transportCost3.setPallet10(328.40);
			transportCost3.setPallet11(348.95);
			transportCost3.setPallet12(368.40);

			TransportCost transportCost4 = new TransportCost();
			transportCost4.setTransportcostId(120l);
			transportCost4.setPallet1(83.75);
			transportCost4.setPallet2(124.30);
			transportCost4.setPallet3(163.50);
			transportCost4.setPallet4(195.90);
			transportCost4.setPallet5(229.70);
			transportCost4.setPallet6(259.45);
			transportCost4.setPallet7(287.70);
			transportCost4.setPallet8(314.85);
			transportCost4.setPallet9(340.50);
			transportCost4.setPallet10(364.75);
			transportCost4.setPallet11(387.80);
			transportCost4.setPallet12(409.40);

			TransportCost transportCost5 = new TransportCost();
			transportCost5.setTransportcostId(150l);
			transportCost5.setPallet1(92.15);
			transportCost5.setPallet2(136.70);
			transportCost5.setPallet3(179.80);
			transportCost5.setPallet4(215.50);
			transportCost5.setPallet5(252.60);
			transportCost5.setPallet6(285.30);
			transportCost5.setPallet7(316.50);
			transportCost5.setPallet8(346.30);
			transportCost5.setPallet9(374.50);
			transportCost5.setPallet10(401.25);
			transportCost5.setPallet11(426.50);
			transportCost5.setPallet12(450.35);

			TransportCost transportCost6 = new TransportCost();
			transportCost6.setTransportcostId(180l);
			transportCost6.setPallet1(100.55);
			transportCost6.setPallet2(149.20);
			transportCost6.setPallet3(196.15);
			transportCost6.setPallet4(235.15);
			transportCost6.setPallet5(275.60);
			transportCost6.setPallet6(311.25);
			transportCost6.setPallet7(345.35);
			transportCost6.setPallet8(377.75);
			transportCost6.setPallet9(408.60);
			transportCost6.setPallet10(437.75);
			transportCost6.setPallet11(465.35);
			transportCost6.setPallet12(491.25);

			TransportCost transportCost7 = new TransportCost();
			transportCost7.setTransportcostId(210l);
			transportCost7.setPallet1(108.95);
			transportCost7.setPallet2(161.55);
			transportCost7.setPallet3(212.50);
			transportCost7.setPallet4(253.90);
			transportCost7.setPallet5(298.60);
			transportCost7.setPallet6(337.15);
			transportCost7.setPallet7(374.15);
			transportCost7.setPallet8(409.30);
			transportCost7.setPallet9(442.60);
			transportCost7.setPallet10(474.25);
			transportCost7.setPallet11(504.05);
			transportCost7.setPallet12(532.15);

			TransportCost transportCost8 = new TransportCost();
			transportCost8.setTransportcostId(240l);
			transportCost8.setPallet1(117.30);
			transportCost8.setPallet2(174.05);
			transportCost8.setPallet3(228.90);
			transportCost8.setPallet4(274.25);
			transportCost8.setPallet5(321.55);
			transportCost8.setPallet6(363.15);
			transportCost8.setPallet7(402.90);
			transportCost8.setPallet8(440.74);
			transportCost8.setPallet9(476.70);
			transportCost8.setPallet10(521.75);
			transportCost8.setPallet11(542.90);
			transportCost8.setPallet12(573.15);

			TransportCost transportCost9 = new TransportCost();
			transportCost9.setTransportcostId(270l);
			transportCost9.setPallet1(125.65);
			transportCost9.setPallet2(186.45);
			transportCost9.setPallet3(245.30);
			transportCost9.setPallet4(293.90);
			transportCost9.setPallet5(344.55);
			transportCost9.setPallet6(389.10);
			transportCost9.setPallet7(431.70);
			transportCost9.setPallet8(472.20);
			transportCost9.setPallet9(510.75);
			transportCost9.setPallet10(547.20);
			transportCost9.setPallet11(581.60);
			transportCost9.setPallet12(614.05);
		
			TransportCost transportCost10 = new TransportCost();
			transportCost10.setTransportcostId(300l);
			transportCost10.setPallet1(134.05);
			transportCost10.setPallet2(198.85);
			transportCost10.setPallet3(261.60);
			transportCost10.setPallet4(313.45);
			transportCost10.setPallet5(367.50);
			transportCost10.setPallet6(415.00);
			transportCost10.setPallet7(460.45);
			transportCost10.setPallet8(503.70);
			transportCost10.setPallet9(544.70);
			transportCost10.setPallet10(583.65);
			transportCost10.setPallet11(620.35);
			transportCost10.setPallet12(655.05);
			
			TransportCost transportCost11 = new TransportCost();
			transportCost11.setTransportcostId(330l);
			transportCost11.setPallet1(142.40);
			transportCost11.setPallet2(211.30);
			transportCost11.setPallet3(277.95);
			transportCost11.setPallet4(332.95);
			transportCost11.setPallet5(390.45);
			transportCost11.setPallet6(441.05);
			transportCost11.setPallet7(489.20);
			transportCost11.setPallet8(535.20);
			transportCost11.setPallet9(578.85);
			transportCost11.setPallet10(620.15);
			transportCost11.setPallet11(659.20);
			transportCost11.setPallet12(695.95);
			
			TransportCost transportCost12 = new TransportCost();
			transportCost12.setTransportcostId(360l);
			transportCost12.setPallet1(150.75);
			transportCost12.setPallet2(223.80);
			transportCost12.setPallet3(294.30);
			transportCost12.setPallet4(352.65);
			transportCost12.setPallet5(413.45);
			transportCost12.setPallet6(466.90);
			transportCost12.setPallet7(518.00);
			transportCost12.setPallet8(566.70);
			transportCost12.setPallet9(612.85);
			transportCost12.setPallet10(656.65);
			transportCost12.setPallet11(697.95);
			transportCost12.setPallet12(736.90);

			transportCostRepository.saveAll(Arrays.asList(transportCost1, transportCost2, transportCost3,
					transportCost4, transportCost5, transportCost6, transportCost7, transportCost8, transportCost9,
					transportCost10, transportCost11, transportCost12));
		}
	}

}
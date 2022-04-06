package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.lems.dto.Role;
import ch.fhnw.lems.dto.UserRole;

// LUM
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(UserRole role);
}
package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.lems.dto.Role;
import ch.fhnw.lems.dto.UserRole;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer> {
	Role findByRole(UserRole role);
}
package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.lems.dto.User;

// LUM
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
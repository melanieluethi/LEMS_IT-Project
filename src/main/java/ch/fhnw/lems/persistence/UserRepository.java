package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.lems.entity.User;

// LUM
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
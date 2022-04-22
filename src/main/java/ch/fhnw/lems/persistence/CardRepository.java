package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.fhnw.lems.entity.Card;

// LUM
public interface CardRepository extends JpaRepository<Card, Long> {
	@Query(value="select card_id from card where user_id =:user_id LIMIT 1", nativeQuery = true)
	Card findByUser(@Param("user_id") Long user_id);
}
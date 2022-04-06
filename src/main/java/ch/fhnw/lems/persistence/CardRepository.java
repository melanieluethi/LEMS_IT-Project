package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.lems.dto.Card;

// LUM
public interface CardRepository extends JpaRepository<Card, Long> {

}
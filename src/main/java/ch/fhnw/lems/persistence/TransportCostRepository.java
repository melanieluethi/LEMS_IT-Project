package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.lems.dto.TransportCost;


//done by HiS
@Repository
public interface TransportCostRepository extends JpaRepository<TransportCost, Integer> {
	
}

package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.lems.dto.CustomerOrder;

// LUM
public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {

}
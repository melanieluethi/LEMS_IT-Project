package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.lems.dto.OrderItem;

// LUM
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
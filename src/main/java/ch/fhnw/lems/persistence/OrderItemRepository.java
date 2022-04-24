package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.lems.entity.OrderItem;

// LUM
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
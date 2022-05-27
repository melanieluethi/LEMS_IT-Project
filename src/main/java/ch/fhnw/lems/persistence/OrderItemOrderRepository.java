package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.lems.entity.OrderItemOrder;

// LUM
public interface OrderItemOrderRepository extends JpaRepository<OrderItemOrder, Long> {

}
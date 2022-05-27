package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.lems.entity.OrderItemCart;

// LUM
public interface OrderItemCartRepository extends JpaRepository<OrderItemCart, Long> {

}
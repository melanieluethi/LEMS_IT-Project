package ch.fhnw.lems.persistence;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.fhnw.lems.entity.CustomerOrder;

// LUM
public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
	@Query(value="select order_id from cart where user_id =:user_id", nativeQuery = true)
	ArrayList<CustomerOrder> findByUserId(@Param("user_id") Long user_id);
}
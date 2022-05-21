package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.fhnw.lems.entity.Cart;

// LUM
public interface CartRepository extends JpaRepository<Cart, Long> {
	@Query(value="select cart_id from lems_database.cart where user_id =:user_id LIMIT 1", nativeQuery = true)
	Long findByUser(@Param("user_id") Long user_id);
}
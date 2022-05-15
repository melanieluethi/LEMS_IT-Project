package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.lems.entity.Shipping;

// LUM
@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}
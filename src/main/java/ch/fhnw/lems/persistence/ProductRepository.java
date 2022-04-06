package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.lems.dto.Product;

// LUM
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
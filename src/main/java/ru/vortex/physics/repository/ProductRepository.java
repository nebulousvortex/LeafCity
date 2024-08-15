package ru.vortex.physics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vortex.physics.model.shop.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

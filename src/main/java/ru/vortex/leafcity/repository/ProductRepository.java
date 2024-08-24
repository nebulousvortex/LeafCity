package ru.vortex.leafcity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vortex.leafcity.model.shop.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

package ru.vortex.leafcity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.vortex.leafcity.model.shop.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p FROM Product p ORDER BY p.orderNum ASC")
    List<Product> findAllSortedByOrder();
}

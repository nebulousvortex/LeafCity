package ru.vortex.leafcity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vortex.leafcity.model.shop.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

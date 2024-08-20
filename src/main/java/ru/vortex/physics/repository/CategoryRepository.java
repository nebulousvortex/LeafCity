package ru.vortex.physics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vortex.physics.model.shop.Category;
import ru.vortex.physics.model.shop.Duration;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

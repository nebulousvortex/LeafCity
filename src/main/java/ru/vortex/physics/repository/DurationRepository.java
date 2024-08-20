package ru.vortex.physics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vortex.physics.model.shop.Duration;
import ru.vortex.physics.model.shop.Product;

public interface DurationRepository extends JpaRepository<Duration, Long> {
}

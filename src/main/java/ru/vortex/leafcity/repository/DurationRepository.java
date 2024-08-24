package ru.vortex.leafcity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vortex.leafcity.model.shop.Duration;

public interface DurationRepository extends JpaRepository<Duration, Long> {
}

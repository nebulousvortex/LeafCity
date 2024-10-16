package ru.vortex.leafcity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vortex.leafcity.model.shop.Promocode;

@Repository
public interface PromocodeRepository extends JpaRepository<Promocode, Long> {
    Promocode findByCode(String code);
}


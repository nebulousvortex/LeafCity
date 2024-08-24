package ru.vortex.leafcity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vortex.leafcity.model.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

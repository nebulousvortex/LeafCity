package ru.vortex.physics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vortex.physics.model.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

package ru.vortex.physics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vortex.physics.model.user.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

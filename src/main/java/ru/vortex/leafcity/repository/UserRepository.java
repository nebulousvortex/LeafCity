package ru.vortex.leafcity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vortex.leafcity.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

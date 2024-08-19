package ru.vortex.physics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vortex.physics.model.user.Role;
import ru.vortex.physics.repository.RoleRepository;

/**
 * Сервис работы с ролями
 */
@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findByName(String roleDoctor) {
        return roleRepository.findByName(roleDoctor);
    }

    public Role findById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow();
    }

    public void createRole(Role role){
        roleRepository.save(role);
    }
}

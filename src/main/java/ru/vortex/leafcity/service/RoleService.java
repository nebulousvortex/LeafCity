package ru.vortex.leafcity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vortex.leafcity.model.user.Role;
import ru.vortex.leafcity.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findByName(String roleDoctor) {
        return roleRepository.findByName(roleDoctor);
    }

    public Role findById(Long roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    public void createRole(Role role){
        roleRepository.save(role);
    }
}

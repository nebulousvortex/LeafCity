package ru.vortex.physics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vortex.physics.model.shop.Category;
import ru.vortex.physics.model.shop.Duration;
import ru.vortex.physics.model.user.Role;
import ru.vortex.physics.repository.CategoryRepository;
import ru.vortex.physics.repository.DurationRepository;
import ru.vortex.physics.repository.RoleRepository;

import java.util.List;

@Service
public class DurationService {
    @Autowired
    DurationRepository durationRepository;
    public Duration findDuration(Long id){
        return durationRepository.findById(id).orElse(null);
    }
    public void createDuration(Duration duration){
        durationRepository.save(duration);
    }
    public void deleteDuration(Duration duration){
        durationRepository.delete(duration);
    }

    public List<Duration> findAll(){
        return durationRepository.findAll();
    }
}

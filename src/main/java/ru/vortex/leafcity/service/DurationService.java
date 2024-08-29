package ru.vortex.leafcity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vortex.leafcity.model.shop.Duration;
import ru.vortex.leafcity.repository.DurationRepository;

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
    public void deleteDuration(Long duration){
        durationRepository.deleteById(duration);
    }

    public List<Duration> findAll(){
        return durationRepository.findAll();
    }
}

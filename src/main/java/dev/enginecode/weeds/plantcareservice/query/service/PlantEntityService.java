package dev.enginecode.weeds.plantcareservice.query.service;

import dev.enginecode.weeds.plantcareservice.query.model.PlantEntity;
import dev.enginecode.weeds.plantcareservice.query.model.PlantJpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlantEntityService {
    private final PlantJpaRepository repository;

    PlantEntityService(final PlantJpaRepository repository) {
        this.repository = repository;
    }

    public PlantEntity findById(UUID id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);

    }
}

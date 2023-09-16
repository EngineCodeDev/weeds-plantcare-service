package dev.enginecode.weeds.plantcareservice.plantentity.query.service;

import dev.enginecode.eccommons.exception.ResourceNotFoundException;
import dev.enginecode.weeds.plantcareservice.plantentity.query.error.ApplicationErrorCode;
import dev.enginecode.weeds.plantcareservice.plantentity.query.model.PlantJpaRepository;
import dev.enginecode.weeds.plantcareservice.plantentity.query.model.PlantEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlantEntityService {
    private final PlantJpaRepository repository;

    PlantEntityService(PlantJpaRepository repository) {
        this.repository = repository;
    }

    public PlantEntity findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Plant not found!", ApplicationErrorCode.PLANT_ENTITY_NOT_FOUND)
                );

    }
}

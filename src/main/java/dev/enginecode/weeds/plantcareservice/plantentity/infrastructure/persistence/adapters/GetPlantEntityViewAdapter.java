package dev.enginecode.weeds.plantcareservice.plantentity.infrastructure.persistence.adapters;

import dev.enginecode.eccommons.exception.ResourceNotFoundException;
import dev.enginecode.weeds.plantcareservice.plantentity.infrastructure.persistence.model.PlantEntityRecord;
import dev.enginecode.weeds.plantcareservice.plantentity.infrastructure.persistence.repository.PlantEntityRecordJpaRepository;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.errors.PresentationErrorCode;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.model.PlantEntityView;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.ports.GetPlantEntityViewPort;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class GetPlantEntityViewAdapter implements GetPlantEntityViewPort {
    PlantEntityRecordJpaRepository repository;

    GetPlantEntityViewAdapter(PlantEntityRecordJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public PlantEntityView findById(UUID id) {
        PlantEntityRecord record = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Plant not found!", PresentationErrorCode.PLANT_ENTITY_NOT_FOUND));
        return new PlantEntityView(
                record.getId(),
                record.getName()
        );

    }
}

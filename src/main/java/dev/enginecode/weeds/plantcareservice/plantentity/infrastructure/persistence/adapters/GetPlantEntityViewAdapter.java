package dev.enginecode.weeds.plantcareservice.plantentity.infrastructure.persistence.adapters;

import dev.enginecode.eccommons.infrastructure.json.repository.JsonRepository;
import dev.enginecode.weeds.plantcareservice.plantentity.infrastructure.persistence.model.PlantEntityRecord;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.model.PlantEntityView;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.ports.GetPlantEntityViewPort;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class GetPlantEntityViewAdapter implements GetPlantEntityViewPort {
    JsonRepository<UUID> repository;

    GetPlantEntityViewAdapter(JsonRepository<UUID> repository) {
        this.repository = repository;
    }

    @Override
    public PlantEntityView findById(UUID id) {
        PlantEntityRecord record = repository.findById(id, PlantEntityRecord.class);
        return new PlantEntityView( record.id(), record.name() );
    }
}

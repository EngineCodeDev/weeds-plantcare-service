package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.adapters;

import dev.enginecode.eccommons.infrastructure.json.repository.JsonRepository;
import dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.model.PlantClassViewRecord;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.ports.GetPlantClassViewPort;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class GetPlantClassViewAdapter implements GetPlantClassViewPort {
    JsonRepository<UUID> repository;

    public GetPlantClassViewAdapter(JsonRepository<UUID> repository) {
        this.repository = repository;
    }

    @Override
    public PlantClassView findById(UUID id) {
        PlantClassViewRecord record = repository.findById(id, PlantClassViewRecord.class);
        return new PlantClassView( record.id(), record.entries() );
    }
}

package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.adapters;

import dev.enginecode.eccommons.infrastructure.json.repository.JsonRepository;
import dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.model.PlantClassViewRecord;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.PresentationPorts;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CreatePlantClassViewAdapter implements PresentationPorts.CreatePlantClassViewPort {
    JsonRepository<UUID> repository;

    public CreatePlantClassViewAdapter(JsonRepository<UUID> repository) {
        this.repository = repository;
    }


    @Override
    public void create(PlantClassView plant) {
        PlantClassViewRecord plantClassViewRecord = new PlantClassViewRecord(plant.id(), plant.entries());
        repository.save(plantClassViewRecord, PlantClassViewRecord.class);
    }
}

package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.adapters;

import dev.enginecode.eccommons.infrastructure.json.repository.JsonRepository;
import dev.enginecode.weeds.plantcareservice.plantcatalog.application.domain.model.PlantClass;
import dev.enginecode.weeds.plantcareservice.plantcatalog.application.domain.ports.CreatePlantClassPort;
import dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.model.PlantClassRecord;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CreatePlantClassAdapter implements CreatePlantClassPort {
    JsonRepository<UUID> repository;

    public CreatePlantClassAdapter(JsonRepository<UUID> repository) {
        this.repository = repository;
    }


    @Override
    public void create(PlantClass plant) {
        PlantClassRecord plantClassRecord = new PlantClassRecord(plant.id(), plant.groups(), plant.entries());
        repository.save(plantClassRecord, PlantClassRecord.class);
    }
}

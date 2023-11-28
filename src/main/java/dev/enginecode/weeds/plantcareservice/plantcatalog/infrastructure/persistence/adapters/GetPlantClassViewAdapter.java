package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.adapters;

import dev.enginecode.eccommons.infrastructure.json.repository.JsonRepository;
import dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.model.PlantClassViewRecord;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.ports.GetPlantClassViewPort;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.InfrastructureConfiguration.PLANT_CLASS_CACHE_MANAGER_NAME;

@Repository
public class GetPlantClassViewAdapter implements GetPlantClassViewPort {
    JsonRepository<UUID> repository;

    public GetPlantClassViewAdapter(JsonRepository<UUID> repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(PLANT_CLASS_CACHE_MANAGER_NAME)
    public PlantClassView findById(UUID id) {
        PlantClassViewRecord record = repository.findById(id, PlantClassViewRecord.class);
        return new PlantClassView( record.id(), record.entries() );
    }

    @Override
    public List<PlantClassView> findAll() {
        List<PlantClassViewRecord> records = repository.findAll(PlantClassViewRecord.class);
        return records.stream()
                .map(record -> new PlantClassView(record.id(), record.entries()))
                .toList();
    }
}

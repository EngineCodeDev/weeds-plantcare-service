package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.adapters;

import dev.enginecode.eccommons.infrastructure.json.repository.JsonRepository;
import dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.model.PlantClassViewRecord;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.PresentationPorts;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.InfrastructureConfiguration.PLANT_CLASS_CACHE_MANAGER_NAME;

@Repository
public class GetPlantClassViewAdapter implements PresentationPorts.GetPlantClassViewPort {
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
    public List<PlantClassView> findBySpecies(String species) {
        List<PlantClassViewRecord> records = repository.findByVirtualColumnLikeIgnoreCase(
                "species", "%"+species+"%", PlantClassViewRecord.class
        );
        return toPlantClassViewList(records);
    }

    @Override
    public List<PlantClassView> findByGenus(String genus) {
        List<PlantClassViewRecord> records = repository.findByVirtualColumnLikeIgnoreCase(
                "genus", "%"+genus+"%", PlantClassViewRecord.class
        );
        return toPlantClassViewList(records);
    }

    @Override
    public List<PlantClassView> findAll() {
        List<PlantClassViewRecord> records = repository.findAll(PlantClassViewRecord.class);
        return toPlantClassViewList(records);
    }


    private List<PlantClassView> toPlantClassViewList(List<PlantClassViewRecord> recordList) {
        return recordList.stream()
                .map(record -> new PlantClassView(record.id(), record.entries()))
                .toList();
    }
}

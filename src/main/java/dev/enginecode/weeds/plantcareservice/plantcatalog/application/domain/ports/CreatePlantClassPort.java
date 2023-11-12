package dev.enginecode.weeds.plantcareservice.plantcatalog.application.domain.ports;

import dev.enginecode.weeds.plantcareservice.plantcatalog.application.domain.model.PlantClass;

public interface CreatePlantClassPort {
    void create(PlantClass plant);
}

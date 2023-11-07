package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.ports;

import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;

public interface CreatePlantClassViewPort {
    void create(PlantClassView plantClassView);
}

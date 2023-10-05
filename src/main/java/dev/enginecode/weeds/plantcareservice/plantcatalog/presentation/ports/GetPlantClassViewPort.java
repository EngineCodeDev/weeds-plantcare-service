package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.ports;

import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;

import java.util.UUID;

public interface GetPlantClassViewPort {
    PlantClassView findById(UUID id);
}

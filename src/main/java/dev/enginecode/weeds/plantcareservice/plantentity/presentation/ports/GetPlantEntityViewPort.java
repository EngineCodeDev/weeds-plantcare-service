package dev.enginecode.weeds.plantcareservice.plantentity.presentation.ports;

import dev.enginecode.weeds.plantcareservice.plantentity.presentation.model.PlantEntityView;

import java.util.UUID;

public interface GetPlantEntityViewPort {
    PlantEntityView findById(UUID id);
}

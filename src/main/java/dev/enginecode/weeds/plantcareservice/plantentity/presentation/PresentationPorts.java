package dev.enginecode.weeds.plantcareservice.plantentity.presentation;

import dev.enginecode.weeds.plantcareservice.plantentity.presentation.model.PlantEntityView;

import java.util.UUID;

public interface PresentationPorts {
    interface GetPlantEntityViewPort {
        PlantEntityView findById(UUID id);
    }
}

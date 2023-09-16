package dev.enginecode.weeds.plantcareservice.plantentity.presentation.queries;

import dev.enginecode.eccommons.cqrs.query.Query;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.model.PlantEntityView;

import java.util.UUID;

public record GetPlantEntityViewQuery(UUID id, String name) implements Query<PlantEntityView> {

    public static GetPlantEntityViewQuery fromId(UUID id) {
        return new GetPlantEntityViewQuery(id, null);
    }
}

package dev.enginecode.weeds.plantcareservice.plantentity.presentation.queries;

import dev.enginecode.eccommons.cqrs.query.Query;
import dev.enginecode.eccommons.structures.model.Response;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.model.PlantEntityView;

import java.util.UUID;

public record GetPlantEntityViewQuery(UUID id, String name) implements Query<Response<PlantEntityView>> {

    public static GetPlantEntityViewQuery byId(UUID id) {
        return new GetPlantEntityViewQuery(id, null);
    }
}

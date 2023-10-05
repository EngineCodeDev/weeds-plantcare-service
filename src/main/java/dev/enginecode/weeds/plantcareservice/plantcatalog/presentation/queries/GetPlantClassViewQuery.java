package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries;

import dev.enginecode.eccommons.cqrs.query.Query;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;

import java.util.UUID;

public record GetPlantClassViewQuery(UUID id) implements Query<PlantClassView> {

    public static GetPlantClassViewQuery fromId(UUID id) {
        return new GetPlantClassViewQuery(id);
    }
}

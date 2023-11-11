package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries;

import dev.enginecode.eccommons.cqrs.query.Query;
import dev.enginecode.eccommons.structures.model.Response;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;

import java.util.UUID;

public record GetPlantClassViewQuery(UUID id) implements Query<Response<PlantClassView>> {

    public static GetPlantClassViewQuery byId(UUID id) {
        return new GetPlantClassViewQuery(id);
    }
    public static GetPlantClassViewQuery all() {
        return new GetPlantClassViewQuery(null);
    }

    public boolean hasId() {
        return id != null;
    }
}

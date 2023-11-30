package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries;

import dev.enginecode.eccommons.cqrs.query.Query;
import dev.enginecode.eccommons.structures.model.Response;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;

import java.util.UUID;

public record GetPlantClassViewQuery(
        UUID id,
        String species,
        String genus
) implements Query<Response<PlantClassView>> {

    public static GetPlantClassViewQuery byId(UUID id) {
        return new GetPlantClassViewQuery(id, null, null);
    }
    public static GetPlantClassViewQuery bySpecies(String species) {
        return new GetPlantClassViewQuery(null, species, null);
    }
    public static GetPlantClassViewQuery byGenus(String genus) {
        return new GetPlantClassViewQuery(null, null, genus);
    }

    public static GetPlantClassViewQuery all() {
        return new GetPlantClassViewQuery(null, null, null);
    }



    public boolean hasId() {
        return id != null;
    }
    public boolean hasSpecies() {
        return species != null;
    }
    public boolean hasGenus() {
        return genus != null;
    }
}

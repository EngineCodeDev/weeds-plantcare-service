package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.controller;

import dev.enginecode.eccommons.structures.model.Response;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.handlers.GetPlantClassViewQueryHandler;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries.GetPlantClassViewQuery;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/plant-classes")
public class GetPlantClassViewEndpoint {
    private final GetPlantClassViewQueryHandler handler;

    public GetPlantClassViewEndpoint(GetPlantClassViewQueryHandler handler) {
        this.handler = handler;
    }

    @GetMapping
    Response<PlantClassView> getAll(
            @RequestParam(required = false) String species,
            @RequestParam(required = false) String genus
    ) {
        return species != null ? handler.handle(GetPlantClassViewQuery.bySpecies(species))
                : genus != null ? handler.handle(GetPlantClassViewQuery.byGenus(genus))
                : handler.handle(GetPlantClassViewQuery.all());
    }

    @GetMapping("/{id}")
    Response<PlantClassView> getById(@PathVariable UUID id) {
        return handler.handle(GetPlantClassViewQuery.byId(id));
    }
}

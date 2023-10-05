package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.controller;

import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.handlers.GetPlantClassViewQueryHandler;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries.GetPlantClassViewQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/plant-classes")
public class GetPlantClassViewEndpoint {
    private final GetPlantClassViewQueryHandler handler;

    public GetPlantClassViewEndpoint(GetPlantClassViewQueryHandler handler) {
        this.handler = handler;
    }

    @GetMapping("/{id}")
    PlantClassView getPlantClass(@PathVariable UUID id) {
        return handler.handle(GetPlantClassViewQuery.fromId(id));
    }
}

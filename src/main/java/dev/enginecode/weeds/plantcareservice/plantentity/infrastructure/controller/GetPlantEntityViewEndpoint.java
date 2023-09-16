package dev.enginecode.weeds.plantcareservice.plantentity.infrastructure.controller;

import dev.enginecode.weeds.plantcareservice.plantentity.presentation.handlers.GetPlantEntityViewQueryHandler;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.model.PlantEntityView;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.queries.GetPlantEntityViewQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/plantentity")
class GetPlantEntityViewEndpoint {
    private final GetPlantEntityViewQueryHandler handler;

    GetPlantEntityViewEndpoint( GetPlantEntityViewQueryHandler handler) {
        this.handler = handler;
    }

    @GetMapping("/{id}")
    ResponseEntity<PlantEntityView> getEntity(@PathVariable UUID id) {
        PlantEntityView plantEntityView = handler.handle(GetPlantEntityViewQuery.fromId(id));
        return ResponseEntity.status(HttpStatus.OK).body(plantEntityView);
    }
}

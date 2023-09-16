package dev.enginecode.weeds.plantcareservice.plantentity.infrastructure.controller;

import dev.enginecode.weeds.plantcareservice.plantentity.presentation.model.PlantEntity;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.service.PlantEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/plantentity")
class GetPlantEntityEndpoint {
    private final PlantEntityService service;

    GetPlantEntityEndpoint(final PlantEntityService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    ResponseEntity<PlantEntity> getEntity(@PathVariable UUID id) {
        PlantEntity plantEntity = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(plantEntity);
    }
}

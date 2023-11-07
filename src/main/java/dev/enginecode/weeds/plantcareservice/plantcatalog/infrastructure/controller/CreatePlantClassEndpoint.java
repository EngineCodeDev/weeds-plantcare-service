package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.controller;

import dev.enginecode.weeds.plantcareservice.plantcatalog.application.commands.CreatePlantClassCommand;
import dev.enginecode.weeds.plantcareservice.plantcatalog.application.handlers.CreatePlantClassCommandHandler;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.enginecode.eccommons.structures.model.Entry;

import java.util.LinkedHashSet;

@RestController
@RequestMapping("/plant-classes")
public class CreatePlantClassEndpoint {
    private final CreatePlantClassCommandHandler handler;

    public CreatePlantClassEndpoint(CreatePlantClassCommandHandler handler) {
        this.handler = handler;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    void getPlantClass(@RequestBody CreatePlantClassPayload plantClassPayload) {
        handler.handle(plantClassPayload.toCommand());
    }

    public record CreatePlantClassPayload(
            LinkedHashSet<String> groups,
            LinkedHashSet<Entry<?>> entries
    ) {
        public CreatePlantClassCommand toCommand() {
            return CreatePlantClassCommand.of(groups, entries);
        }
    }
}

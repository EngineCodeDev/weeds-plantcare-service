package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.controller;

import dev.enginecode.eccommons.structures.validation.EntriesPayload;
import dev.enginecode.weeds.plantcareservice.plantcatalog.application.commands.CreatePlantClassCommand;
import dev.enginecode.weeds.plantcareservice.plantcatalog.application.handlers.CreatePlantClassCommandHandler;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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
    void getPlantClass(@RequestBody @Valid CreatePlantClassPayload plantClassPayload) {
        handler.handle(plantClassPayload.toCommand());
    }

    public record CreatePlantClassPayload(
            @NotEmpty(message = "cannot be empty")
            LinkedHashSet<String> groups,
            @NotEmpty(message = "cannot be empty")
            LinkedHashSet<Entry<?>> entries
    ) implements EntriesPayload {
        public CreatePlantClassCommand toCommand() {
            return CreatePlantClassCommand.of(groups, entries);
        }
    }
}

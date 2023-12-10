package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.controller;

import dev.enginecode.eccommons.structures.validation.EntriesPayload;
import dev.enginecode.weeds.plantcareservice.plantcatalog.application.commands.CreatePlantClassCommand;
import dev.enginecode.weeds.plantcareservice.plantcatalog.application.handlers.CreatePlantClassCommandHandler;
import dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.controller.validators.CreatePlantClassPayloadValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import dev.enginecode.eccommons.structures.model.Entry;

import java.util.LinkedHashSet;

@RestController
@RequestMapping("/plant-classes")
public class CreatePlantClassEndpoint {
    private final CreatePlantClassCommandHandler handler;
    private final CreatePlantClassPayloadValidator plantClassPayloadValidator;

    public CreatePlantClassEndpoint(
            CreatePlantClassCommandHandler handler, CreatePlantClassPayloadValidator plantClassPayloadValidator
    ) {
        this.handler = handler;
        this.plantClassPayloadValidator = plantClassPayloadValidator;
    }

    @InitBinder
    void initEntriesPayloadValidator(WebDataBinder binder) {
        binder.addValidators(plantClassPayloadValidator);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    void getPlantClass(@Valid @RequestBody CreatePlantClassPayload plantClassPayload) {
        handler.handle(plantClassPayload.toCommand());
    }

    public record CreatePlantClassPayload(
            @NotEmpty(message = "cannot be empty")
            LinkedHashSet<String> groups,
            @NotEmpty(message = "cannot be empty")
            LinkedHashSet<Entry<?>> entries
    ) implements EntriesPayload {
        public CreatePlantClassPayload {
            groups = groups == null ? new LinkedHashSet<>() : groups;
            entries = entries == null ? new LinkedHashSet<>() : entries;
        }

        public CreatePlantClassCommand toCommand() {
            return CreatePlantClassCommand.of(groups, entries);
        }
    }
}

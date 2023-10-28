package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.controller;

import dev.enginecode.eccommons.jsonschema.model.JsonSchema;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.handlers.GetJsonSchemaQueryHandler;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries.GetJsonSchemaQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json-schemas")
public class GetJsonSchemaEndpoint {
    private final GetJsonSchemaQueryHandler handler;

    public GetJsonSchemaEndpoint(GetJsonSchemaQueryHandler handler) {
        this.handler = handler;
    }

    @GetMapping
    JsonSchema getAllDictionaries() {
        return handler.handle(GetJsonSchemaQuery.all());
    }
}

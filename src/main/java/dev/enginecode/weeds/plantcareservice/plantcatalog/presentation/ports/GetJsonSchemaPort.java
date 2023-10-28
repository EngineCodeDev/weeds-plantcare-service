package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.ports;

import dev.enginecode.eccommons.jsonschema.model.JsonSchema;

public interface GetJsonSchemaPort {
    JsonSchema findAll();
}

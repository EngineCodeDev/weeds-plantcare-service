package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries;

import dev.enginecode.eccommons.cqrs.query.Query;
import dev.enginecode.eccommons.jsonschema.model.JsonSchema;

public record GetJsonSchemaQuery() implements Query<JsonSchema> {

    public static GetJsonSchemaQuery all() {
        return new GetJsonSchemaQuery();
    }
}

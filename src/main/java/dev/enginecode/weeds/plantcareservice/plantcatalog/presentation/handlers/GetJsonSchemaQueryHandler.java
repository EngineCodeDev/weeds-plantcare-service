package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.handlers;

import dev.enginecode.eccommons.cqrs.query.QueryHandler;
import dev.enginecode.eccommons.jsonschema.model.JsonSchema;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.ports.GetJsonSchemaPort;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries.GetJsonSchemaQuery;
import org.springframework.stereotype.Component;

@Component
public class GetJsonSchemaQueryHandler implements QueryHandler<JsonSchema, GetJsonSchemaQuery> {

    GetJsonSchemaPort port;

    public GetJsonSchemaQueryHandler(GetJsonSchemaPort port) {
        this.port = port;
    }

    @Override
    public JsonSchema handle(GetJsonSchemaQuery query) {
        return port.findAll();
    }
}

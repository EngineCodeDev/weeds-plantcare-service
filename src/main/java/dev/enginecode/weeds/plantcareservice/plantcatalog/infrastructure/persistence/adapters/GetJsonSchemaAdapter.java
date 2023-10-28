package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.adapters;

import dev.enginecode.eccommons.exception.ResourceNotFoundException;
import dev.enginecode.eccommons.infrastructure.json.repository.JsonRepository;
import dev.enginecode.eccommons.jsonschema.model.JsonSchema;
import dev.enginecode.weeds.plantcareservice.plantcatalog.errors.PlantCatalogErrorCode;
import dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.model.JsonSchemaRecord;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.ports.GetJsonSchemaPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class GetJsonSchemaAdapter implements GetJsonSchemaPort {
    JsonRepository<UUID> repository;

    public GetJsonSchemaAdapter(JsonRepository<UUID> repository) {
        this.repository = repository;
    }

    @Override
    public JsonSchema findAll() {
        List<JsonSchemaRecord> recordList = repository.findAll(JsonSchemaRecord.class);
        if (!recordList.isEmpty()) {
            JsonSchemaRecord jsonSchemaRecord = recordList.get(0);
            return new JsonSchema(jsonSchemaRecord.id(), jsonSchemaRecord.properties(), jsonSchemaRecord.options());
        }
        throw new ResourceNotFoundException("json-schema not found", PlantCatalogErrorCode.RESOURCE_NOT_FOUND);
    }
}

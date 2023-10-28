package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.model;

import dev.enginecode.eccommons.infrastructure.json.model.TableAnnotatedRecord;
import dev.enginecode.eccommons.infrastructure.json.model.TableName;
import dev.enginecode.eccommons.jsonschema.model.Entry;
import dev.enginecode.eccommons.jsonschema.model.JsonSchema;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.UUID;

@TableName("gc_json_schema")
public record JsonSchemaRecord(
        UUID id,
        LinkedHashMap<String, JsonSchema.Settings> properties,
        LinkedHashMap<String, LinkedHashSet<Entry<?>>> options
) implements TableAnnotatedRecord<UUID> {
}

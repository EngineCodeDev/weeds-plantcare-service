package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.model;

import dev.enginecode.eccommons.infrastructure.json.model.TableAnnotatedRecord;
import dev.enginecode.eccommons.infrastructure.json.model.TableName;
import dev.enginecode.eccommons.structures.model.Entry;

import java.util.LinkedHashSet;
import java.util.UUID;

@TableName("gc_plant_class")
public record PlantClassRecord(
        UUID id,
        LinkedHashSet<String> groups,
        LinkedHashSet<Entry<?>> entries
) implements TableAnnotatedRecord<UUID> {

}

package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.model;

import dev.enginecode.eccommons.infrastructure.json.model.TableAnnotatedRecord;
import dev.enginecode.eccommons.infrastructure.json.model.TableName;
import dev.enginecode.eccommons.model.Entry;

import java.util.LinkedHashSet;
import java.util.UUID;

@TableName("pc_plant_class_view")
public record PlantClassViewRecord(
        UUID id,
        LinkedHashSet<Entry<?>> entries
) implements TableAnnotatedRecord {}

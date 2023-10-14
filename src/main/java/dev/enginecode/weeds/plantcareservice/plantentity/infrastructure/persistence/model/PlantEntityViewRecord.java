package dev.enginecode.weeds.plantcareservice.plantentity.infrastructure.persistence.model;

import dev.enginecode.eccommons.infrastructure.json.model.TableAnnotatedRecord;
import dev.enginecode.eccommons.infrastructure.json.model.TableName;

import java.util.UUID;

@TableName("ps_plant_entity_view")
public record PlantEntityViewRecord(
        UUID id,
        String name
) implements TableAnnotatedRecord {}

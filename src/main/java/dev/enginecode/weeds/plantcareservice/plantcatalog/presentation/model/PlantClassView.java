package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model;

import dev.enginecode.eccommons.jsonschema.model.Entry;

import java.util.LinkedHashSet;
import java.util.UUID;

public record PlantClassView(UUID id, LinkedHashSet<Entry<?>> entries) {
}

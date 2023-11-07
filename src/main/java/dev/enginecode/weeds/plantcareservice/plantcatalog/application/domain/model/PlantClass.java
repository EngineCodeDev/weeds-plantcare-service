package dev.enginecode.weeds.plantcareservice.plantcatalog.application.domain.model;

import dev.enginecode.eccommons.structures.model.Entry;

import java.util.LinkedHashSet;
import java.util.UUID;

public record PlantClass(UUID id, LinkedHashSet<String> groups, LinkedHashSet<Entry<?>> entries) {
}

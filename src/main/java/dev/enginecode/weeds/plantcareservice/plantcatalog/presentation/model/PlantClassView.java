package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model;

import dev.enginecode.eccommons.model.Entry;

import java.util.LinkedHashSet;
import java.util.UUID;

public class PlantClassView {
    private final UUID id;
    private final LinkedHashSet<Entry<?>> entries;

    public PlantClassView(UUID id, LinkedHashSet<Entry<?>> entries) {
        this.id = id;
        this.entries = entries;
    }

    public UUID getId() {
        return id;
    }

    public LinkedHashSet<Entry<?>> getEntries() {
        return entries;
    }
}

package dev.enginecode.weeds.plantcareservice.plantentity.presentation.model;

import java.util.UUID;

public class PlantEntityView {
    private final UUID id;
    private final String name;

    public PlantEntityView(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

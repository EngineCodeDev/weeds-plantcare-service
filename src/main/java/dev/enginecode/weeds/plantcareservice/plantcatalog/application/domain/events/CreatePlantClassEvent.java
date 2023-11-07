package dev.enginecode.weeds.plantcareservice.plantcatalog.application.domain.events;

import dev.enginecode.eccommons.structures.model.Entry;
import org.springframework.context.ApplicationEvent;

import java.util.LinkedHashSet;
import java.util.UUID;

public class CreatePlantClassEvent extends ApplicationEvent {

    private final UUID id;
    private final LinkedHashSet<String> groups;
    private final LinkedHashSet<Entry<?>> entries;

    public CreatePlantClassEvent(Object source, final UUID id, final LinkedHashSet<String> groups, final LinkedHashSet<Entry<?>> entries) {
        super(source);
        this.id = id;
        this.groups = groups;
        this.entries = entries;
    }

    public UUID getId() {
        return id;
    }

    public LinkedHashSet<String> getGroups() {
        return groups;
    }

    public LinkedHashSet<Entry<?>> getEntries() {
        return entries;
    }
}

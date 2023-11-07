package dev.enginecode.weeds.plantcareservice.plantcatalog.application.commands;

import dev.enginecode.eccommons.cqrs.command.Command;
import dev.enginecode.eccommons.structures.model.Entry;
import dev.enginecode.weeds.plantcareservice.plantcatalog.application.domain.model.PlantClass;

import java.util.LinkedHashSet;

public record CreatePlantClassCommand(
        LinkedHashSet<String> groups,
        LinkedHashSet<Entry<?>> entries
) implements Command<PlantClass> {

    public static CreatePlantClassCommand of(LinkedHashSet<String> groups, LinkedHashSet<Entry<?>> entries) {
        return new CreatePlantClassCommand(groups, entries);
    }

}

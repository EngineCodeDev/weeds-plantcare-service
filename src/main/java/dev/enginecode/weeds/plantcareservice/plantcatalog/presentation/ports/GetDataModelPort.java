package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.ports;

import dev.enginecode.eccommons.structures.model.DataModel;

import java.util.Set;

public interface GetDataModelPort {
    DataModel findOne();


    DataModel findByGroups(Set<String> groups);
}

package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.ports;

import dev.enginecode.eccommons.structures.model.DataModel;

public interface GetDataModelPort {
    DataModel findOne();
}

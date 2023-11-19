package dev.enginecode.weeds.plantcareservice.plantcatalog.application.domain;

import dev.enginecode.eccommons.structures.model.DataModel;
import dev.enginecode.weeds.plantcareservice.plantcatalog.application.domain.model.PlantClass;

public interface ApplicationPorts {

    interface CreatePlantClassPort {
        void create(PlantClass plant);
    }

    interface GetDataModelPort {
        DataModel findOne();
    }

}

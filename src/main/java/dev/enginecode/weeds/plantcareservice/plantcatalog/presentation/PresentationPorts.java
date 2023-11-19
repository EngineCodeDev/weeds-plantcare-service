package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation;

import dev.enginecode.eccommons.structures.model.DataModel;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;

import java.util.List;
import java.util.UUID;

public interface PresentationPorts {
    interface CreatePlantClassViewPort {
        void create(PlantClassView plantClassView);
    }

    interface GetDataModelPort {
        DataModel findOne();
    }

    interface GetPlantClassViewPort {

        PlantClassView findById(UUID id);

        List<PlantClassView> findBySpecies(String species);

        List<PlantClassView> findByGenus(String genus);

        List<PlantClassView> findAll();
    }
}

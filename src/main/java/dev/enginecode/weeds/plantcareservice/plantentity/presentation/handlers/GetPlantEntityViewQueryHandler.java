package dev.enginecode.weeds.plantcareservice.plantentity.presentation.handlers;

import dev.enginecode.eccommons.cqrs.query.QueryHandler;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.model.PlantEntityView;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.ports.GetPlantEntityViewPort;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.queries.GetPlantEntityViewQuery;
import org.springframework.stereotype.Component;

@Component
public class GetPlantEntityViewQueryHandler implements QueryHandler<PlantEntityView, GetPlantEntityViewQuery> {
    GetPlantEntityViewPort port;

    GetPlantEntityViewQueryHandler(GetPlantEntityViewPort port) {
        this.port = port;
    }

    @Override
    public PlantEntityView handle(GetPlantEntityViewQuery query) {
        return port.findById(query.id());
    }
}

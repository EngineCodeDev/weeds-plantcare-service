package dev.enginecode.weeds.plantcareservice.plantentity.presentation.handlers;

import dev.enginecode.eccommons.cqrs.query.QueryHandler;
import dev.enginecode.eccommons.structures.model.Response;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.model.PlantEntityView;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.ports.GetPlantEntityViewPort;
import dev.enginecode.weeds.plantcareservice.plantentity.presentation.queries.GetPlantEntityViewQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPlantEntityViewQueryHandler implements QueryHandler<Response<PlantEntityView>, GetPlantEntityViewQuery> {
    GetPlantEntityViewPort port;

    GetPlantEntityViewQueryHandler(GetPlantEntityViewPort port) {
        this.port = port;
    }

    @Override
    public Response<PlantEntityView> handle(GetPlantEntityViewQuery query) {
        PlantEntityView plantEntityView = port.findById(query.id());
        return new Response<>(
                List.of( plantEntityView )
        );
    }
}

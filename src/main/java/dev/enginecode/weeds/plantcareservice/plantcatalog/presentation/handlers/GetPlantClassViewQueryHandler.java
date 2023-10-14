package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.handlers;

import dev.enginecode.eccommons.cqrs.query.QueryHandler;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.ports.GetPlantClassViewPort;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries.GetPlantClassViewQuery;
import org.springframework.stereotype.Component;

@Component
public class GetPlantClassViewQueryHandler implements QueryHandler<PlantClassView, GetPlantClassViewQuery> {

    GetPlantClassViewPort port;

    public GetPlantClassViewQueryHandler(GetPlantClassViewPort port) {
        this.port = port;
    }

    @Override
    public PlantClassView handle(GetPlantClassViewQuery query) {
        return port.findById(query.id());
    }
}

package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.handlers;

import dev.enginecode.eccommons.cqrs.query.QueryHandler;
import dev.enginecode.eccommons.structures.model.Response;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.ports.GetPlantClassViewPort;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries.GetPlantClassViewQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPlantClassViewQueryHandler implements QueryHandler<Response<PlantClassView>, GetPlantClassViewQuery> {

    GetPlantClassViewPort port;

    public GetPlantClassViewQueryHandler(GetPlantClassViewPort port) {
        this.port = port;
    }

    @Override
    public Response<PlantClassView> handle(GetPlantClassViewQuery query) {
        List<PlantClassView> items = query.hasId() ? List.of(port.findById(query.id())) : port.findAll();
        return new Response<>(items);
    }
}

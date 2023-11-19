package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.handlers;

import dev.enginecode.eccommons.cqrs.query.QueryHandler;
import dev.enginecode.eccommons.structures.model.Response;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.PresentationPorts;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries.GetPlantClassViewQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPlantClassViewQueryHandler implements QueryHandler<Response<PlantClassView>, GetPlantClassViewQuery> {

    PresentationPorts.GetPlantClassViewPort port;

    public GetPlantClassViewQueryHandler(PresentationPorts.GetPlantClassViewPort port) {
        this.port = port;
    }

    @Override
    public Response<PlantClassView> handle(GetPlantClassViewQuery query) {
        List<PlantClassView> items =
                query.hasId() ? List.of(port.findById(query.id()))
                        : query.hasSpecies() ? port.findBySpecies(query.species())
                        : query.hasGenus() ? port.findByGenus(query.genus())
                        : port.findAll();
        return new Response<>(items);
    }
}

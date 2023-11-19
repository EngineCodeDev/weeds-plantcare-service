package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.handlers;

import dev.enginecode.eccommons.cqrs.query.QueryHandler;
import dev.enginecode.eccommons.structures.model.DataModel;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.PresentationPorts;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries.GetDataModelQuery;
import org.springframework.stereotype.Component;

@Component
public class GetDataModelQueryHandler implements QueryHandler<DataModel, GetDataModelQuery> {

    PresentationPorts.GetDataModelPort port;

    public GetDataModelQueryHandler(PresentationPorts.GetDataModelPort port) {
        this.port = port;
    }

    @Override
    public DataModel handle(GetDataModelQuery query) {
        return port.findOne();
    }
}

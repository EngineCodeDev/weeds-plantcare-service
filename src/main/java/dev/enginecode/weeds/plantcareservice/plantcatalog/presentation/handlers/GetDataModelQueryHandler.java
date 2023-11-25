package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.handlers;

import dev.enginecode.eccommons.cqrs.query.QueryHandler;
import dev.enginecode.eccommons.structures.model.DataModel;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.ports.GetDataModelPort;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries.GetDataModelQuery;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;


@Component
public class GetDataModelQueryHandler implements QueryHandler<DataModel, GetDataModelQuery> {

    GetDataModelPort port;

    public GetDataModelQueryHandler(GetDataModelPort port) {
        this.port = port;
    }

    @Override
    @Cacheable(DATA_MODEL_CACHE_MANAGER_NAME)
    public DataModel handle(GetDataModelQuery query) {
        return port.findOne();
    }
}

package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries;

import dev.enginecode.eccommons.cqrs.query.Query;
import dev.enginecode.eccommons.structures.model.DataModel;

public record GetDataModelQuery() implements Query<DataModel> {}

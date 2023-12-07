package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries;

import dev.enginecode.eccommons.cqrs.query.Query;
import dev.enginecode.eccommons.structures.model.DataModel;

import java.util.Set;

public record GetDataModelQuery(Set<String> groups) implements Query<DataModel> {

    public static GetDataModelQuery all() {
        return new GetDataModelQuery(null);
    }

    public static GetDataModelQuery byGroups(Set<String> groups) {
        return new GetDataModelQuery(groups);
    }

    public boolean hasGroups() {
        return groups != null && !groups.isEmpty();
    }
}

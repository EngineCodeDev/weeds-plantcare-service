package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.controller;

import dev.enginecode.eccommons.structures.model.DataModel;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.handlers.GetDataModelQueryHandler;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.queries.GetDataModelQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/data-model")
public class GetDataModelEndpoint {
    private final GetDataModelQueryHandler handler;

    public GetDataModelEndpoint(GetDataModelQueryHandler handler) {
        this.handler = handler;
    }

    @GetMapping
    DataModel getDataModel(@RequestParam(required = false) Set<String> groups) {
        return (groups == null || groups.isEmpty()) ?
                handler.handle(GetDataModelQuery.all()) :
                handler.handle(GetDataModelQuery.byGroups(groups));
    }
}

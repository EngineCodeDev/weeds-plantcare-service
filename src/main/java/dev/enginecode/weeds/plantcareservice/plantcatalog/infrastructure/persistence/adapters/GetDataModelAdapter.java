package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.adapters;

import dev.enginecode.eccommons.exception.ResourceNotFoundException;
import dev.enginecode.eccommons.infrastructure.json.repository.JsonRepository;
import dev.enginecode.eccommons.structures.model.DataModel;
import dev.enginecode.eccommons.structures.model.Entry;
import dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.model.DataModelRecord;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.ports.GetDataModelPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static dev.enginecode.eccommons.exception.EngineCodeExceptionGroup.INFRASTRUCTURE_ERROR;
import static dev.enginecode.eccommons.exception.ResourceNotFoundException.NOT_FOUND_ANY_DETAILED;
import static dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.InfrastructureConfiguration.DATA_MODEL_CACHE_MANAGER_NAME;

@Repository
public class GetDataModelAdapter implements GetDataModelPort {
    private final static Logger logger = LogManager.getLogger(GetDataModelAdapter.class);
    JsonRepository<UUID> repository;

    public GetDataModelAdapter(JsonRepository<UUID> repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(DATA_MODEL_CACHE_MANAGER_NAME)
    public DataModel findOne() {
        List<DataModelRecord> recordList = repository.findAll(DataModelRecord.class);
        if (!recordList.isEmpty()) {
            DataModelRecord dataModelRecord = recordList.get(0);
            return new DataModel(
                    dataModelRecord.id(),
                    dataModelRecord.entrySettings(),
                    dataModelRecord.groupContents(),
                    dataModelRecord.enumOptions()
            );
        }
        logger.error(String.format(NOT_FOUND_ANY_DETAILED, recordList + " for type: " + DataModelRecord.class));
        throw new ResourceNotFoundException(INFRASTRUCTURE_ERROR, String.format(NOT_FOUND_ANY_DETAILED, "Data Model"));
    }

    @Override
    public DataModel findByGroups(Set<String> groups) {
        DataModel one = findOne();

        LinkedHashMap<String, LinkedHashSet<String>> groupContents = one.groupContents().entrySet()
                .stream()
                .filter(groupContent -> groups.contains(groupContent.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key1, key2) -> key1, LinkedHashMap::new));

        Set<String> filteredContent = groupContents.values().stream().flatMap(Set::stream).collect(Collectors.toSet());

        LinkedHashMap<String, DataModel.EntrySettings> entrySettings = one.entrySettings().entrySet()
                .stream()
                .filter(entry -> filteredContent.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key1, key2) -> key1, LinkedHashMap::new));

        LinkedHashMap<String, LinkedHashSet<Entry<?>>> enumOptions = one.enumOptions().entrySet()
                .stream()
                .filter(options -> filteredContent.contains(options.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key1, key2) -> key1, LinkedHashMap::new));

        return new DataModel(one.id(), entrySettings, groupContents, enumOptions);
    }

}

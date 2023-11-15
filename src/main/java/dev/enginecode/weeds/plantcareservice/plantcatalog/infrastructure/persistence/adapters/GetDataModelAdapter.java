package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.adapters;

import dev.enginecode.eccommons.exception.ResourceNotFoundException;
import dev.enginecode.eccommons.infrastructure.json.repository.JsonRepository;
import dev.enginecode.eccommons.structures.model.DataModel;
import dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.model.DataModelRecord;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.ports.GetDataModelPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static dev.enginecode.eccommons.exception.EngineCodeExceptionGroup.INFRASTRUCTURE_ERROR;
import static dev.enginecode.eccommons.exception.ResourceNotFoundException.NOT_FOUND_ANY;
import static dev.enginecode.eccommons.exception.ResourceNotFoundException.NOT_FOUND_ANY_DETAILED;

@Repository
public class GetDataModelAdapter implements GetDataModelPort {
    private final static Logger logger = LogManager.getLogger(GetDataModelAdapter.class);
    JsonRepository<UUID> repository;

    public GetDataModelAdapter(JsonRepository<UUID> repository) {
        this.repository = repository;
    }

    @Override
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
        throw new ResourceNotFoundException(INFRASTRUCTURE_ERROR, NOT_FOUND_ANY);
    }
}

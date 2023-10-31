package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.adapters;

import dev.enginecode.eccommons.exception.ResourceNotFoundException;
import dev.enginecode.eccommons.infrastructure.json.repository.JsonRepository;
import dev.enginecode.eccommons.structures.model.DataModel;
import dev.enginecode.weeds.plantcareservice.plantcatalog.errors.PlantCatalogErrorCode;
import dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.model.DataModelRecord;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.ports.GetDataModelPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class GetDataModelAdapter implements GetDataModelPort {
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
        throw new ResourceNotFoundException("Data Model not found", PlantCatalogErrorCode.RESOURCE_NOT_FOUND);
    }

}

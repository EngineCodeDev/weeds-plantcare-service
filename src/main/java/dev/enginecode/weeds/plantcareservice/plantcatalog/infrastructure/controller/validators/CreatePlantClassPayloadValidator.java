package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.controller.validators;

import dev.enginecode.eccommons.structures.model.DataModel;
import dev.enginecode.eccommons.structures.validation.EntriesPayloadDataModelValidator;
import dev.enginecode.weeds.plantcareservice.plantcatalog.application.domain.ApplicationPorts;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CreatePlantClassPayloadValidator extends EntriesPayloadDataModelValidator {
    private final ApplicationPorts.GetDataModelPort getDataModelPort;

    public CreatePlantClassPayloadValidator(ApplicationPorts.GetDataModelPort getDataModelPort, ApplicationContext applicationContext) {
        super(applicationContext);
        this.getDataModelPort = getDataModelPort;
    }

    @Override
    public DataModel getDataModel() {
        return getDataModelPort.findOne();
    }
}

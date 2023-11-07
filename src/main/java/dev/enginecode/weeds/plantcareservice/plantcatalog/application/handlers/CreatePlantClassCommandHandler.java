package dev.enginecode.weeds.plantcareservice.plantcatalog.application.handlers;

import dev.enginecode.eccommons.cqrs.command.CommandHandler;
import dev.enginecode.weeds.plantcareservice.plantcatalog.application.commands.CreatePlantClassCommand;
import dev.enginecode.weeds.plantcareservice.plantcatalog.application.domain.events.CreatePlantClassEvent;
import dev.enginecode.weeds.plantcareservice.plantcatalog.application.domain.model.PlantClass;
import dev.enginecode.weeds.plantcareservice.plantcatalog.application.domain.ports.CreatePlantClassPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreatePlantClassCommandHandler implements CommandHandler<PlantClass, CreatePlantClassCommand> {


    private final ApplicationEventPublisher applicationEventPublisher;
    private final CreatePlantClassPort port;

    CreatePlantClassCommandHandler(ApplicationEventPublisher applicationEventPublisher, CreatePlantClassPort port) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.port = port;
    }

    @Override
    public void handle(CreatePlantClassCommand command) {
        PlantClass plantClass = new PlantClass(UUID.randomUUID(), command.groups(), command.entries());
        port.create(plantClass);
        applicationEventPublisher.publishEvent(
                new CreatePlantClassEvent(
                        this,
                        plantClass.id(),
                        plantClass.groups(),
                        plantClass.entries()
                )
        );
    }
}

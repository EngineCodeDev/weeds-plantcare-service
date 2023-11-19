package dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.handlers.listeners;

import dev.enginecode.weeds.plantcareservice.plantcatalog.application.domain.events.CreatePlantClassEvent;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.PresentationPorts;
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CreatePlantClassEventListener implements ApplicationListener<CreatePlantClassEvent> {

    private final PresentationPorts.CreatePlantClassViewPort port;

    public CreatePlantClassEventListener(PresentationPorts.CreatePlantClassViewPort port) {
        this.port = port;
    }

    @Override
    public void onApplicationEvent(CreatePlantClassEvent event) {
        PlantClassView plantClassView = new PlantClassView(event.getId(), event.getEntries());
        port.create(plantClassView);
    }
}

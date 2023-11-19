package dev.enginecode.weeds.plantcareservice;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("dev.enginecode.eccommons.infrastructure.json.model")
@EnableJpaRepositories("dev.enginecode.eccommons.infrastructure.json.repository")
@ComponentScan({"dev.enginecode"})
public class WeedsPlantcareServiceApplicationConfiguration {


}

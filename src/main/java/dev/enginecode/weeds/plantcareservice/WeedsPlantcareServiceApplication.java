package dev.enginecode.weeds.plantcareservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"dev.enginecode"})
public class WeedsPlantcareServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeedsPlantcareServiceApplication.class, args);
	}

}

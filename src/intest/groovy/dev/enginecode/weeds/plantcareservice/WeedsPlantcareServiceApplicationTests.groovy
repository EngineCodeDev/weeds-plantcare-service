package dev.enginecode.weeds.plantcareservice

import dev.enginecode.weeds.plantcareservice.intestspec.IntestSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

class WeedsPlantcareServiceApplicationTests extends IntestSpecification{

    @Autowired
    private ApplicationContext applicationContext

    def "load context"() {
        expect:
        println("Application: " + applicationContext.getId())
    }

}

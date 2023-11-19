package dev.enginecode.weeds.plantcareservice.plantcatalog

import dev.enginecode.weeds.plantcareservice.intestspec.IntestSpecification
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

class CreatePlantClassEndpointTest extends IntestSpecification {

    private static final def CREATE_PLANT_CLASS_PAYLOAD = """
        {
          "groups": ["trees"],
          "entries": [
            {"key": "name", "value": "Oak", "type": "string", "info": "Very popular tree"},
            {"key": "species", "value": "Quercus robur", "type": "string", "info": ""},
            {"key": "genus", "value": "Quercus", "type": "string", "info": ""},
            {"key": "average age", "value": "300", "type": "string", "info": "It's quite a lot"}
          ]
        }
      """

    private static final def EXPECTED_PLANT_CLASS_VIEW_JSON = """
        {
          "items": [
            {
              "entries": [
                  {"key": "name", "value": "Oak", "type": "string", "info": "Very popular tree"},
                  {"key": "species", "value": "Quercus robur", "type": "string", "info": ""},
                  {"key": "genus", "value": "Quercus", "type": "string", "info": ""},
                  {"key": "average age", "value": "300", "type": "string", "info": "It's quite a lot"}
                ]
            }
          ]
        }
      """

    def "should save plant class to database"() {
        when:
        def response = testRestTemplate.exchange(
                "http://localhost:$port/plantcare-service/plant-classes",
                HttpMethod.POST,
                getRequestEntity(CREATE_PLANT_CLASS_PAYLOAD),
                String.class
        )

        then:
        response.statusCode == HttpStatus.OK

        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/plant-classes",
                String.class
        )

        then:
        JSONAssert.assertEquals(EXPECTED_PLANT_CLASS_VIEW_JSON, responseEntity.body, JSONCompareMode.LENIENT)
    }

}


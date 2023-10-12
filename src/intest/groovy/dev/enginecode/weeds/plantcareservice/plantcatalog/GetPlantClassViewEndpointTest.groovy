package dev.enginecode.weeds.plantcareservice.plantcatalog

import dev.enginecode.eccommons.handler.ErrorResponse
import dev.enginecode.weeds.plantcareservice.intestspec.IntestSpecification
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.http.HttpStatus

class GetPlantClassViewEndpointTest extends IntestSpecification {

    private static final def PLANT_ID = "e8e31639-bd65-46af-a594-fa59aa510bdf"
    private static final EXPECTED_JSON = """
        {
          "id": "e8e31639-bd65-46af-a594-fa59aa510bdf",
          "entries": [
            {"key": "Key1", "value": "Value1", "type": "string"},
            {"key": "Key2", "value": "Value2", "type": "string"},
            {"key": "Key3", "value": "Value3", "type": "string"}
          ]
        }
    """
    private static final def NON_EXISTING_PLANT_ID = "00000000-1111-1111-1111-000000000000"

    def setup() {
        dbAdmin.runSqlFile("db/runtime-sql/plant-classes-dml.sql")
    }

    def "should get plant class with the given id from database"() {
        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/plant-classes/$PLANT_ID",
                String.class
        )
        println(responseEntity.body)
        then:
        JSONAssert.assertEquals(EXPECTED_JSON, responseEntity.body, JSONCompareMode.LENIENT)
    }

    def "should throw exception when plant class with the given id not found"() {
        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/plant-classes/$NON_EXISTING_PLANT_ID",
                ErrorResponse.class
        )
        println(responseEntity.body)

        then:
        responseEntity.statusCode == HttpStatus.NOT_FOUND
        responseEntity.body.code() == "RESOURCE_NOT_FOUND"
        responseEntity.body.message() == "Resource with id: '$NON_EXISTING_PLANT_ID' not found!"
    }
}


package dev.enginecode.weeds.plantcareservice.plantentity

import dev.enginecode.eccommons.handler.ErrorResponse
import dev.enginecode.weeds.plantcareservice.intestspec.IntestSpecification
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.http.HttpStatus

class GetPlantEntityViewEndpointTest extends IntestSpecification {

    private static final def ALDER_ID = "ccabd618-b415-4457-b355-aada204f6b8c"
    private static final def NON_EXISTING_PLANT_ID = "00000000-1111-1111-1111-000000000000"
    private static final EXPECTED_ALDER_JSON = """{"items": [{"id":"ccabd618-b415-4457-b355-aada204f6b8c","name":"alder"}]}"""

    def setup() {
        dbAdmin.runSqlFile("db/runtime-sql/plant-entities-dml.sql")
    }

    def "should retrieve the plant entity from database"() {
        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/plant-entities/$ALDER_ID",
                String.class
        )

        then:
        JSONAssert.assertEquals(EXPECTED_ALDER_JSON, responseEntity.body, JSONCompareMode.LENIENT)
    }

    def "should throw exception when plant entity with the given id not found"() {
        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/plant-entities/$NON_EXISTING_PLANT_ID",
                ErrorResponse.class
        )

        then:
        responseEntity.statusCode == HttpStatus.NOT_FOUND
        responseEntity.body.code() == "RESOURCE_NOT_FOUND"
        responseEntity.body.message() == "Resource with id: '$NON_EXISTING_PLANT_ID' not found!"
    }

}

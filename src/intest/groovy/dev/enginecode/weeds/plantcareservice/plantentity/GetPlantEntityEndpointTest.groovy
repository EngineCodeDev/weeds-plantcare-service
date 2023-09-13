package dev.enginecode.weeds.plantcareservice.plantentity

import dev.enginecode.weeds.plantcareservice.intestspec.IntestSpecification
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode

class GetPlantEntityEndpointTest extends IntestSpecification {

    private static final def ALDER_ID = "ccabd618-b415-4457-b355-aada204f6b8c"
    private static final EXPECTED_ALDER_JSON = """{"id":"ccabd618-b415-4457-b355-aada204f6b8c","name":"alder"}"""

    def "should retrieve the plant entity from database"() {
        given:
        dbAdmin.runSqlFile("db/runtime-sql/plant-entities-dml.sql")

        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/plantentity/$ALDER_ID",
                String.class
        )

        then:
        JSONAssert.assertEquals(EXPECTED_ALDER_JSON, responseEntity.body, JSONCompareMode.LENIENT)
    }
}

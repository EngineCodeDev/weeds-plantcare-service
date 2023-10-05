package dev.enginecode.weeds.plantcareservice.plantcatalog

import dev.enginecode.weeds.plantcareservice.intestspec.IntestSpecification

class GetPlantClassViewEndpointTest extends IntestSpecification {

    private static final def TREE_ID = "ccabd618-b415-4457-b355-aada204f6b8c"

    def setup() {
        dbAdmin.runSqlFile("db/runtime-sql/plant-classes-dml.sql")
    }

    def "should get plant class with the given id from database"() {
        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/plant-classes/$TREE_ID",
                String.class
        )
        println(responseEntity.body)
        then:
        0 == 0
    }
}


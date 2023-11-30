package dev.enginecode.weeds.plantcareservice.plantcatalog

import dev.enginecode.eccommons.handler.ErrorResponse
import dev.enginecode.weeds.plantcareservice.intestspec.IntestSpecification
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.http.HttpStatus

class GetPlantClassViewEndpointTest extends IntestSpecification {

    private static final def PLANT_ID = "e8e31639-bd65-46af-a594-fa59aa510bdf"
    private static final EXPECTED_PLANT_CLASS_VIEW_RESPONSE = """
        {
          "items": [
            {
              "id": "e8e31639-bd65-46af-a594-fa59aa510bdf",
              "entries": [
                {"key": "species", "value": "Acer saccharum", "type": "string"},
                {"key": "genus", "value": "Acer", "type": "string"},
                {"key": "Key3", "value": "Value3", "type": "string"}
              ]
            }
          ]
        }
    """
    private static final def NON_EXISTING_PLANT_CLASS_VIEW_ID = "00000000-1111-1111-1111-000000000000"

    private static final def EXPECTED_ALL_PLANT_CLASS_VIEWS_RESPONSE = """
        {
          "items": [
            {
              "id": "6ea8fed8-2d86-4e0c-8593-d33314a86747", 
              "entries": [
                {"key": "species", "value": "Quercus robur", "type": "string", "info": null}, 
                {"key": "genus", "value": "Quercus", "type": "string", "info": null},
                {"key": "Key3", "value": "Value3", "type": "string", "info": null}
              ]
            },
            {
              "id": "ccabd618-b415-4457-b355-aada204f6b8c", 
              "entries": [
                {"key": "species", "value": "Quercus durata", "type": "string", "info": null}, 
                {"key": "genus", "value": "Quercus", "type": "string", "info": null},
                {"key": "Key3", "value": "Value3", "type": "string", "info": null}
                ]
            },
            {
              "id": "0a0279a1-6182-4ad6-ae81-fd783528ac18", 
              "entries": [
                {"key": "species", "value": "Quercus lobata", "type": "string", "info": null}, 
                {"key": "genus", "value": "Quercus", "type": "string", "info": null},
                {"key": "Key3", "value": "Value3", "type": "string", "info": null}
                ]
            },
            {
              "id": "e8e31639-bd65-46af-a594-fa59aa510bdf", 
              "entries": [
                {"key": "species", "value": "Acer saccharum", "type": "string", "info": null}, 
                {"key": "genus", "value": "Acer", "type": "string", "info": null},
                {"key": "Key3", "value": "Value3", "type": "string", "info": null}
              ]
            }
          ]
        }
    """

    private static final def EXPECTED_PLANT_CLASS_VIEWS_BY_GENUS_RESPONSE = """
        {
          "items": [
            {
              "id": "6ea8fed8-2d86-4e0c-8593-d33314a86747", 
              "entries": [
                {"key": "species", "value": "Quercus robur", "type": "string", "info": null}, 
                {"key": "genus", "value": "Quercus", "type": "string", "info": null},
                {"key": "Key3", "value": "Value3", "type": "string", "info": null}
              ]
            },
            {
              "id": "ccabd618-b415-4457-b355-aada204f6b8c", 
              "entries": [
                {"key": "species", "value": "Quercus durata", "type": "string", "info": null}, 
                {"key": "genus", "value": "Quercus", "type": "string", "info": null},
                {"key": "Key3", "value": "Value3", "type": "string", "info": null}
                ]
            },
            {
              "id": "0a0279a1-6182-4ad6-ae81-fd783528ac18", 
              "entries": [
                {"key": "species", "value": "Quercus lobata", "type": "string", "info": null}, 
                {"key": "genus", "value": "Quercus", "type": "string", "info": null},
                {"key": "Key3", "value": "Value3", "type": "string", "info": null}
                ]
            }
          ]
        }
    """

    private static final def EXPECTED_PLANT_CLASS_VIEW_BY_SPECIES_RESPONSE = """
        {
          "items": [
            {
              "id": "e8e31639-bd65-46af-a594-fa59aa510bdf", 
              "entries": [
                {"key": "species", "value": "Acer saccharum", "type": "string", "info": null}, 
                {"key": "genus", "value": "Acer", "type": "string", "info": null},
                {"key": "Key3", "value": "Value3", "type": "string", "info": null}
              ]
            }
          ]
        }
    """

    def setup() {
        dbAdmin.runSqlFile("db/runtime-sql/plant-classes-dml.sql")
    }

    def "should get plant class with the given id from database"() {
        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/plant-classes/$PLANT_ID",
                String.class
        )

        then:
        JSONAssert.assertEquals(EXPECTED_PLANT_CLASS_VIEW_RESPONSE, responseEntity.body, JSONCompareMode.LENIENT)
    }

    def "should get plant classes with the given genus from database"() {
        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/plant-classes?genus=que",
                String.class
        )

        then:
        JSONAssert.assertEquals(EXPECTED_PLANT_CLASS_VIEWS_BY_GENUS_RESPONSE, responseEntity.body, JSONCompareMode.LENIENT)
    }

    def "should get plant classes with the given species from database"() {
        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/plant-classes?species=cer",
                String.class
        )

        then:
        JSONAssert.assertEquals(EXPECTED_PLANT_CLASS_VIEW_BY_SPECIES_RESPONSE, responseEntity.body, JSONCompareMode.LENIENT)
    }

    def "should get all plant classes from database"() {
        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/plant-classes",
                String.class
        )

        then:
        JSONAssert.assertEquals(EXPECTED_ALL_PLANT_CLASS_VIEWS_RESPONSE, responseEntity.body, JSONCompareMode.LENIENT)
    }

    def "should throw exception when plant class with the given id not found"() {
        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/plant-classes/$NON_EXISTING_PLANT_CLASS_VIEW_ID",
                ErrorResponse.class
        )

        then:
        responseEntity.statusCode == HttpStatus.NOT_FOUND
        responseEntity.body.code() == "INFRASTRUCTURE_ERROR"
        responseEntity.body.message() == "Resource with id: '$NON_EXISTING_PLANT_CLASS_VIEW_ID' not found!"
    }
}


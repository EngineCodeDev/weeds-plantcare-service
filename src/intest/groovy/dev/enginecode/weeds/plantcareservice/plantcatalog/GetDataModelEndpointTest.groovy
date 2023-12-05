package dev.enginecode.weeds.plantcareservice.plantcatalog

import dev.enginecode.eccommons.handler.ErrorResponse
import dev.enginecode.weeds.plantcareservice.intestspec.IntestSpecification
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.http.HttpStatus

class GetDataModelEndpointTest extends IntestSpecification {

    private static final EXPECTED_JSON = """
        {
          "id": "8fd1980d-3151-4d00-8495-5a10bf8e7099",
          "entrySettings": {
            "genus": {"type": "string", "format": null, "optionsRef": null, "required": true, "readOnly": true},
            "family": {"type": "string", "format": null, "optionsRef": null, "required": true, "readOnly": true},
            "commonNames": {"type": "string_array", "format": null, "optionsRef": null, "required": true, "readOnly": false},
            "lifecycle": {"type": "enum_key", "format": "dictionary", "optionsRef": "lifecycle", "required": true, "readOnly": false},
            "origin": {"type": "enum_key_array", "format": "dictionary", "optionsRef": "origin", "required": true, "readOnly": false},
            "something": {"type": "enum_key", "format": "dynamic", "optionsRef": "/app-resources/somethings", "required": true, "readOnly": false},
            "wikiLinks": {"type": "string", "format": "url", "optionsRef": null, "required": true, "readOnly": false},
            "imageSource": {"type": "string", "format": "url", "optionsRef": null, "required": true, "readOnly": true},
            "somethingNew": {"type": "string", "format": "url", "optionsRef": null, "required": true, "readOnly": true}
          },
          "groupContents": {
            "potted plant": ["genus", "family", "commonNames", "lifecycle", "origin", "something", "wikiLinks", "imageSource", "somethingNew"],
            "trees": ["genus", "family", "commonNames"],
            "ground plant": ["genus", "family", "commonNames"]
          },
          "enumOptions": {
            "origin": [
              {"key": "origin_asia", "value": "Asia", "info": ""},
              {"key": "origin_europe", "value": "Europe", "info": ""},
              {"key": "origin_southamerica", "value": "South America", "info": ""},
              {"key": "origin_northamerica", "value": "North America", "info": ""},
              {"key": "origin_africa", "value": "Africa", "info": ""},
              {"key": "origin_australia", "value": "Australia", "info": ""},
              {"key": "origin_antarctica", "value": "Antarctica", "info": ""}
            ],
            "lifecycle": [
              {"key": "lifecycle_annual", "value": "Annual", "info": ""},
              {"key": "lifecycle_perennial", "value": "Perennial", "info": ""},
              {"key": "lifecycle_biennial", "value": "Biennial", "info": ""}
            ],
            "substrate": [
              {"key": "substrate_soil", "value": "Soil", "info": ""},
              {"key": "substrate_perlite", "value": "Perlite", "info": ""},
              {"key": "substrate_vermiculite", "value": "Vermiculite", "info": ""}
            ]
          }
        }
    """


    def "should get data model from database"() {
        given:
        dbAdmin.runSqlFile("db/runtime-sql/gc-data-model-dml.sql")

        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/data-model",
                String.class
        )

        then:
        JSONAssert.assertEquals(EXPECTED_JSON, responseEntity.body, JSONCompareMode.LENIENT)
    }

    def "should throw exception when data model not found"() {
        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/data-model",
                ErrorResponse.class
        )

        then:
        responseEntity.statusCode == HttpStatus.NOT_FOUND
        responseEntity.body.code() == "INFRASTRUCTURE_ERROR"
        responseEntity.body.message() == "'Data Model' was not found."
    }
}


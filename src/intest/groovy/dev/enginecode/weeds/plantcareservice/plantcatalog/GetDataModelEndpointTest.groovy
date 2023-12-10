package dev.enginecode.weeds.plantcareservice.plantcatalog

import dev.enginecode.eccommons.handler.ErrorResponse
import dev.enginecode.weeds.plantcareservice.intestspec.IntestSpecification
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext


class GetDataModelEndpointTest extends IntestSpecification {

    private static final EXPECTED_JSON = """
        {
          "id": "8fd1980d-3151-4d00-8495-5a10bf8e7099",
          "entrySettings": {
            "status": {"type":  "enum_key", "format": "dictionary", "optionsRef": null, "unique": false, "required": true, "readOnly": false},
            "species": {"type": "string", "format": null, "optionsRef": null, "unique": true, "required": true, "readOnly": true},
            "genus": {"type": "string", "format": null, "optionsRef": null, "unique": false, "required": true, "readOnly": true},
            "family": {"type": "string", "format": null, "optionsRef": null, "unique": false, "required": true, "readOnly": true},
            "commonNames": {"type": "string_array", "format": null, "optionsRef": null, "unique": false, "required": true, "readOnly": false},
            "lifecycle": {"type": "enum_key", "format": "dictionary", "optionsRef": "lifecycle", "unique": false, "required": true, "readOnly": false},
            "origin": {"type": "enum_key_array", "format": "dictionary", "optionsRef": "origin", "unique": false, "required": true, "readOnly": false},
            "something": {"type": "enum_key", "format": "dynamic", "optionsRef": "/app-resources/somethings", "unique": false, "required": true, "readOnly": false},
            "wikiLinks": {"type": "string", "format": "url", "optionsRef": null, "unique": false, "required": true, "readOnly": false},
            "imageSource": {"type": "string", "format": "url", "optionsRef": null, "unique": false, "required": true, "readOnly": true},
            "somethingNew": {"type": "string", "format": "url", "optionsRef": null, "unique": false, "required": true, "readOnly": true}
          },
          "groupContents": {
            "potted plant": ["species", "genus", "family", "commonNames", "lifecycle", "origin", "something", "wikiLinks", "imageSource", "somethingNew"],
            "trees": ["species", "genus", "family", "commonNames"],
            "ground plant": ["genus", "family", "commonNames", "lifecycle"]
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

    private static final BY_GROUPS_EXPECTED_JSON = """
        {
          "id": "8fd1980d-3151-4d00-8495-5a10bf8e7099",
          "entrySettings": {
            "species": {"type": "string", "format": null, "optionsRef": null, "unique": true, "required": true, "readOnly": true},
            "genus": {"type": "string", "format": null, "optionsRef": null, "required": true, "readOnly": true},
            "family": {"type": "string", "format": null, "optionsRef": null, "required": true, "readOnly": true},
            "commonNames": {"type": "string_array", "format": null, "optionsRef": null, "required": true, "readOnly": false},
            "lifecycle": {"type": "enum_key", "format": "dictionary", "optionsRef": "lifecycle", "required": true, "readOnly": false}
          },
          "groupContents": {
            "trees": ["species", "genus", "family", "commonNames"],
            "ground plant": ["genus", "family", "commonNames", "lifecycle"]
          },
          "enumOptions": {
            "lifecycle": [
              {"key": "lifecycle_annual", "value": "Annual", "info": ""},
              {"key": "lifecycle_perennial", "value": "Perennial", "info": ""},
              {"key": "lifecycle_biennial", "value": "Biennial", "info": ""}
            ]
          }
        }
    """


    def "should get data model from database"() {
        given:
        dbAdmin.runSqlFile("db/runtime-sql/gc-data-model-dml.sql")

        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/data-model$query",
                String.class
        )

        then:
        JSONAssert.assertEquals(EXPECTED_JSON, responseEntity.body, JSONCompareMode.LENIENT)

        where:
        description         | query
        "No query provided" | ""
        "Empty query"       | "?query="
    }

    def "should get data model by groups"() {
        given:
        dbAdmin.runSqlFile("db/runtime-sql/gc-data-model-dml.sql")

        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/data-model$query",
                String.class
        )

        then:
        JSONAssert.assertEquals(BY_GROUPS_EXPECTED_JSON, responseEntity.body, JSONCompareMode.LENIENT)

        where:
        description                          | query
        "Query with existing groups"         | "?groups=trees,ground plant"
        "Query with one non existing groups" | "?groups=trees,ground plant,non existing"
        "Query with duplicated groups"       | "?groups=trees,ground plant,trees"
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
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


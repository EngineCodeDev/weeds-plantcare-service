package dev.enginecode.weeds.plantcareservice.plantcatalog

import dev.enginecode.eccommons.handler.ErrorResponse
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import spock.lang.Unroll

class CreatePlantClassEndpointValidationTest extends CreatePlantClassEndpointValidationTestData {

    @Unroll("#payloadDescription")
    def "should respond with error for invalid payload"() {
        given:
        dbAdmin.runSqlFile("db/runtime-sql/gc-data-model-dml.sql")

        when:
        def response = testRestTemplate.exchange(
                "http://localhost:$port/plantcare-service/plant-classes",
                HttpMethod.POST,
                getRequestEntity(payload),
                ErrorResponse.class
        )

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
        response.body.hasFieldErrors()
        response.body.code() == "VALIDATION_ERROR"
        response.body.fieldErrors().size() == 2
        response.body.fieldErrors().any { it.field() == "groups" }
        response.body.fieldErrors().any { it.field() == "entries" }
        response.body.fieldErrors().each { it.errorCode() == "NotEmpty" }
        response.body.fieldErrors().each { it.defaultMessage() == "cannot be empty" }

        where:
        payloadDescription                      | payload
        "payload with empty groups and entries" | CREATE_PAYLOAD_EMPTY
        "payload with null groups and entries"  | CREATE_PAYLOAD_NULL
    }

    @Unroll("#payloadDescription")
    def "should respond with error for invalid payload"() {
        given:
        dbAdmin.runSqlFile("db/runtime-sql/gc-data-model-dml.sql")

        when:
        def response = testRestTemplate.exchange(
                "http://localhost:$port/plantcare-service/plant-classes",
                HttpMethod.POST,
                getRequestEntity(payload),
                ErrorResponse.class
        )

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
        response.body.hasFieldErrors()
        response.body.code() == "VALIDATION_ERROR"
        response.body.fieldErrors().size() == 1
        response.body.fieldErrors().get(0).field() == field
        response.body.fieldErrors().get(0).errorCode() == errorCode
        response.body.fieldErrors().get(0).defaultMessage() == fieldMessage

        where:
        payloadDescription                 | payload                            | field     || errorCode             | fieldMessage
        "payload with unknown group"       | CREATE_PAYLOAD_UNKNOWN_GROUP       | "groups"  || "UNKNOWN_GROUPS"      | "[unknown group] not found"
        "payload with unknown groups"      | CREATE_PAYLOAD_UNKNOWN_GROUPS      | "groups"  || "UNKNOWN_GROUPS"      | "[unknown group 1, unknown group 2] not found"
        "payload with unexpected contents" | CREATE_PAYLOAD_UNEXPECTED_CONTENTS | "entries" || "UNEXPECTED_CONTENTS" | "[origin] not expected for groups [trees, ground plant]"
        "payload with wrong entry type"    | CREATE_PAYLOAD_WRONG_ENTRY_TYPE    | "entries" || "INVALID_ENTRY_TYPE"  | "[species] should be of type STRING but is ENUM_KEY"
    }

}


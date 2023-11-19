package dev.enginecode.weeds.plantcareservice.plantcatalog

import dev.enginecode.eccommons.handler.ErrorResponse
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

class CreatePlantClassEndpointValidationTest extends CreatePlantClassEndpointValidationTestData {

    def "should respond with error for invalid payload"() {
        when:
        def response = testRestTemplate.exchange(
                "http://localhost:$port/plantcare-service/plant-classes",
                HttpMethod.POST,
                getRequestEntity(payload),
                ErrorResponse.class
        )

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
        response.body.hasErrors()
        response.body.code() == "VALIDATION_ERROR"
        response.body.errors().get(field) == "cannot be empty"

        where:
        payload                      | field
        CREATE_PAYLOAD_EMPTY_GROUPS  | "groups"
        CREATE_PAYLOAD_NULL_GROUPS   | "groups"
        CREATE_PAYLOAD_EMPTY_ENTRIES | "entries"
        CREATE_PAYLOAD_NULL_ENTRIES  | "entries"
    }

}


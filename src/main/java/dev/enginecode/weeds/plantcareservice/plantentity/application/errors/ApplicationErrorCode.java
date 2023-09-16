package dev.enginecode.weeds.plantcareservice.plantentity.application.errors;


import dev.enginecode.eccommons.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum ApplicationErrorCode implements ErrorCode {
    PLANT_ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND);

    private final HttpStatus httpStatus;

    ApplicationErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}

package dev.enginecode.weeds.common.query.exception;

import org.springframework.http.HttpStatus;

public enum ApplicationErrorCode {
    PLANT_ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND);

    private  HttpStatus httpStatus;

    ApplicationErrorCode(final HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

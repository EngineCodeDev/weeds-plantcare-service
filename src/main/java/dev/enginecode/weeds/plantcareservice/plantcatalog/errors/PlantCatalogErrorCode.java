package dev.enginecode.weeds.plantcareservice.plantcatalog.errors;

import dev.enginecode.eccommons.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum PlantCatalogErrorCode implements ErrorCode {
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND);

    private final HttpStatus httpStatus;

    PlantCatalogErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

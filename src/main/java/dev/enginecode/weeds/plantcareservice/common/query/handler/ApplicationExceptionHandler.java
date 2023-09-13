package dev.enginecode.weeds.plantcareservice.common.query.handler;

import dev.enginecode.weeds.plantcareservice.common.query.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApplicationErrorResponse> handle(ResourceNotFoundException e) {
        return ResponseEntity
                .status(e.getApplicationErrorCode().getHttpStatus())
                .body(new ApplicationErrorResponse(e.getApplicationErrorCode(), e.getMessage()));
    }

}

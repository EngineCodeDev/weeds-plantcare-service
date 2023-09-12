package dev.enginecode.weeds.common.query.handler;

import dev.enginecode.weeds.common.query.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApplicationExceptionHandler {

    public ResponseEntity<ApplicationErrorResponse> handle(ResourceNotFoundException e) {
        e.getMessage();
        ;
        return ResponseEntity
                .status(e.getApplicationErrorCode().getHttpStatus())
                .body(new ApplicationErrorResponse(e.getApplicationErrorCode(), e.getMessage()));
    }

}

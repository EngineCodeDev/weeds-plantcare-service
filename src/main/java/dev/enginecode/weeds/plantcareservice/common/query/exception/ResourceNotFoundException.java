package dev.enginecode.weeds.plantcareservice.common.query.exception;

public class ResourceNotFoundException extends RuntimeException {
    private ApplicationErrorCode applicationErrorCode;

    public ResourceNotFoundException(final String message, ApplicationErrorCode applicationErrorCode) {
        super(message);
        this.applicationErrorCode = applicationErrorCode;
    }

    public ApplicationErrorCode getApplicationErrorCode() {
        return applicationErrorCode;
    }
}

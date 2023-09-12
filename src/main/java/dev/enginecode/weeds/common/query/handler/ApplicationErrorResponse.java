package dev.enginecode.weeds.common.query.handler;

import dev.enginecode.weeds.common.query.exception.ApplicationErrorCode;

class ApplicationErrorResponse {
    private final ApplicationErrorCode applicationErrorCode;
    private final String message;

    ApplicationErrorResponse(final ApplicationErrorCode applicationErrorCode, final String message) {
        this.applicationErrorCode = applicationErrorCode;
        this.message = message;
    }
}

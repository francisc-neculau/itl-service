package org.itl.service.external.icr;

public class IcrServiceException extends RuntimeException {

    public IcrServiceException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public IcrServiceException(String message) {
        super(message);
    }
}

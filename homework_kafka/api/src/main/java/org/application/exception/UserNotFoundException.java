package org.application.exception;

import static java.lang.String.format;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final String message, final Object o) {
        super(format(message, o));
    }
}

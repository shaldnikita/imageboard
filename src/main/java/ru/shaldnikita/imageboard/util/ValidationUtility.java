package ru.shaldnikita.imageboard.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ValidationUtility {

    public static void validate(boolean arg, String msg) {
        if(!arg) {
            throw new ValidationFailedException(msg);
        }
    }

    public static void validate(boolean arg) {
        if(!arg) {
            throw new ValidationFailedException();
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class ValidationFailedException extends RuntimeException {
        public ValidationFailedException(String message) {
            super(message);
        }

        public ValidationFailedException() {
            super();
        }
    }
}

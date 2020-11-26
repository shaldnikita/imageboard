package ru.shaldnikita.imageboard.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ThreadNotFoundException extends RuntimeException{

    public ThreadNotFoundException(long id) {
        super("Thread with id=" + id + " not found.");
    }
}

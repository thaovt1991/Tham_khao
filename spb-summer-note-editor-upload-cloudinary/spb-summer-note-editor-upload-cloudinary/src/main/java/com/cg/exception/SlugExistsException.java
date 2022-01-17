package com.cg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class SlugExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SlugExistsException(String message) {
        super(message);
    }
}

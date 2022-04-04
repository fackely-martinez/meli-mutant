package com.example.melimutant.core.person.exception;

public class NoContentException extends RuntimeException {

    private static final long serialVersionUID = -6848101875489318065L;

    public NoContentException(final String method) {
        super(String.format("%s : There aren't people in the repository", method));
    }
}

package com.example.melimutant.core.person.exception;

public class NoMutantException extends RuntimeException {
    private static final long serialVersionUID = -1256302875018343359L;

    public NoMutantException(final String method, final String personID) {
        super(String.format("%s: Person %s is not a mutant", method, personID));
    }
}

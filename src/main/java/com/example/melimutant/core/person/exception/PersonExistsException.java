package com.example.melimutant.core.person.exception;

public class PersonExistsException extends RuntimeException {
    private static final long serialVersionUID = -1245302875018343359L;

    public PersonExistsException(final String method, final String personID) {
        super(String.format("%s: Person %s already exists", method, personID));
    }
}

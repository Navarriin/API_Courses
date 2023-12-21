package com.navarro.courses.exceptions;

public class RecordNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(Long id) {
        super("Registo de id " + id + " não encontrado");
    }
}

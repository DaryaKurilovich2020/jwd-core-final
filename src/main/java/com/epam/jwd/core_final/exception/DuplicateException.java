package com.epam.jwd.core_final.exception;

public class DuplicateException extends Exception {
    @Override
    public String getMessage() {
        return "Entity with this name already exists";
    }
}
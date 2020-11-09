package com.epam.jwd.core_final.exception;

public class AssignException extends Exception{
    @Override
    public String getMessage() {
        return "You can't assign this entity on a mission";
    }
}

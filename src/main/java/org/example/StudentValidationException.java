package org.example;

public class StudentValidationException extends StudentRepositoryException{
    public StudentValidationException(String message) {
        super(message);
    }
}

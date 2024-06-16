package org.example;

public class StudentNotFoundException extends StudentRepositoryException{
    public StudentNotFoundException(String message) {
        super(message);
    }
}

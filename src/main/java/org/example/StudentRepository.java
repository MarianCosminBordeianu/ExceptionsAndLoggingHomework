package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class StudentRepository {
    private static final Logger logger = Logger.getLogger(StudentRepository.class.getName());
    private final HashMap<String, Student> students = new HashMap<>();

    public void addStudent(Student student) throws StudentValidationException {
        if (student == null || student.getCnp() == null || student.getCnp().isEmpty()) {
            logger.severe("Student or ID is empty");
            throw new StudentValidationException("Student or ID is empty.");
        }
        students.put(student.getCnp(), student);
        logger.info("Added student: " + student.getCnp());
    }

    public void deleteStudent(String cnp) throws StudentNotFoundException, StudentValidationException {
        if (cnp == null || cnp.isEmpty()) {
            logger.severe("ID is empty");
            throw new StudentValidationException("ID is empty.");
        }
        if (!students.containsKey(cnp)) {
            logger.severe("Student does not exist: " + cnp);
            throw new StudentNotFoundException("Student does not exist.");
        }
        students.remove(cnp);
        logger.info("Deleted student: " + cnp);
    }

    public List<Student> retrieveStudentsByAge(int age) throws StudentValidationException {
        if (age < 0) {
            logger.severe("Age is negative: " + age);
            throw new StudentValidationException("Age cannot be negative.");
        }
        List<Student> result = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        logger.info("Retrieved students of age: " + age);
        return result;
    }

    public List<Student> listStudents(boolean orderByLastName) throws StudentValidationException {
        if (students.isEmpty()) {
            logger.severe("No students available to list");
            throw new StudentValidationException("No students available to list.");
        }
        List<Student> result = new ArrayList<>(students.values());
        if (orderByLastName) {
            result.sort(Comparator.comparing(Student::getLastName));
        } else {
            result.sort(Comparator.comparing(Student::getDateOfBirth));
        }
        logger.info("Listed students ordered by " + (orderByLastName ? "last name" : "birth date"));
        return result;
    }
}


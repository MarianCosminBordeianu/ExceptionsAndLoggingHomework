package org.example;

import junit.framework.TestCase;


import java.time.LocalDate;
import java.util.List;


public class AppTest 
    extends TestCase
{
    public void testAddInvalidStudent() {
        try {
            StudentRepository repository = new StudentRepository();
            Student student = new Student("", "George", LocalDate.of(2000, 3, 9), "male", "1234567891212");
            repository.addStudent(student);
        } catch (StudentValidationException e) {
            assertTrue(true);
        }
    }

    public void testDeleteStudent() {
        try {
            StudentRepository repository = new StudentRepository();
            Student student1 = new Student("Cosmin", "George", LocalDate.of(2000, 3, 9), "male", "1234567891212");
            Student student2 = new Student("Ion", "Pop", LocalDate.of(1995, 8, 25), "male", "9876543212345");

            repository.addStudent(student1);
            repository.addStudent(student2);
            repository.deleteStudent("1234567891212");

            List<Student> students = repository.listStudents(true);
            assertEquals(1, students.size());
        } catch (StudentValidationException | StudentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

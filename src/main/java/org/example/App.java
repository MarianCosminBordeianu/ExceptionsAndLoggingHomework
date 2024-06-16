package org.example;

import java.time.LocalDate;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
        try {
            StudentRepository repository = new StudentRepository();

            Student student1 = new Student("Cosmin", "George", LocalDate.of(2000, 3, 9), "male", "1234567891212");
            Student student2 = new Student("Ion", "Pop", LocalDate.of(1995, 8, 25), "male", "9876543212345");

            repository.addStudent(student1);
            repository.addStudent(student2);

            List<Student> studentsByName = repository.listStudents(true);
            for (Student s : studentsByName) {
                System.out.println(s.getLastName() + " " + s.getFirstName());
            }

            System.out.println("-------------------------------------------");

            List<Student> studentsByBirthDate = repository.listStudents(false);
            for (Student student : studentsByBirthDate) {
                System.out.println(student.getDateOfBirth() + ": " + student.getFirstName() + " " + student.getLastName());
            }

            System.out.println("-------------------------------------------");

            List<Student> studentsAge24 = repository.retrieveStudentsByAge(24);
            for (Student s : studentsAge24) {
                System.out.println(s.getFirstName() + " " + s.getLastName() + " is 24 years old");
            }

            System.out.println("-------------------------------------------");

            repository.deleteStudent("1234567891212");
            List<Student> students = repository.listStudents(true);
            for (Student s : students) {
                System.out.println(s.getLastName() + " " + s.getFirstName());
            }

        } catch (StudentRepositoryException e) {
            throw new RuntimeException(e);
        }
    }
}

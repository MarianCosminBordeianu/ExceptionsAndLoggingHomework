package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.util.logging.Logger;

public class Student {
    private static final Logger logger = Logger.getLogger(Student.class.getName());

    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final String gender;
    private final String cnp;

    public Student(String firstName, String lastName, LocalDate dateOfBirth, String gender, String cnp) throws StudentValidationException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.cnp = cnp;
        validate();
    }

    private void validate() throws StudentValidationException {
        int currentYear = LocalDate.now().getYear();
        int birthYear = dateOfBirth.getYear();
        if (birthYear < 1900 || birthYear > currentYear - 18) {
            logger.severe("Invalid date of birth: " + dateOfBirth);
            throw new StudentValidationException("Date of birth must be between 1900 and current year - 18.");
        }
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            logger.severe("First name or last name is empty");
            throw new StudentValidationException("First name and last name must not be empty.");
        }
        if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female") && !gender.equalsIgnoreCase("m") && !gender.equalsIgnoreCase("f")) {
            logger.severe("Invalid gender: " + gender);
            throw new StudentValidationException("Gender must be either male or female (or M/F).");
        }
        if(cnp == null || cnp.length() != 13){
            logger.severe("Invalid cnp: " + cnp);
            throw new StudentValidationException("CNP length must be 13 characters.");
        }
        logger.info("Student validation successful");
    }

    public int getAge() {
        LocalDate today = LocalDate.now();
        int age = Period.between(dateOfBirth, today).getYears();
        logger.fine("Calculated age for " + firstName + " " + lastName + ": " + age);
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCnp() {
        return cnp;
    }
}

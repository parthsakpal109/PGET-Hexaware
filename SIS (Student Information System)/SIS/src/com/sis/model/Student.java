package com.sis.model;

public class Student {
    int studentId;
    String firstName;
    String lastName;
    String dateOfBirth;
    String email;
    String phoneNumber;

    public Student(int studentId, String firstName, String lastName, String dateOfBirth, String email, String phoneNumber) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void displayStudentInfo() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("DOB: " + dateOfBirth);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }

    public static void main(String[] args) {
        // Creating a student object
        Student s1 = new Student(1, "Amit", "Verma", "2003-05-20", "amit.verma@example.com", "9876543210");

        // Display student information
        s1.displayStudentInfo();
    }
}

package com.hexaware.sis.entity;

import java.time.LocalDate;

public class Enrollment {
    private int enrollmentId;
    private Student student;
    private Course course;
    private LocalDate enrollmentDate;

    // Default constructor
    public Enrollment() {
    }

    // Parameterized constructor
    public Enrollment(int enrollmentId, Student student, Course course, LocalDate enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and Setters
    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    // Utility
    public void displayEnrollmentInfo() {
        System.out.println("Enrollment ID: " + enrollmentId);
        System.out.println("Student ID: " + (student != null ? student.getStudentId() : "N/A"));
        System.out.println("Course ID: " + (course != null ? course.getCourseId() : "N/A"));
        System.out.println("Enrollment Date: " + enrollmentDate);
    }
}

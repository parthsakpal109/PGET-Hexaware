package com.sis.model;
import java.util.*;

class Teacher {
    int teacherId;
    String firstName;
    String lastName;
    String email;
    List<Course> assignedCourses = new ArrayList<>();

    public Teacher(int teacherId, String firstName, String lastName, String email) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void displayTeacherInfo() {
        System.out.println("Teacher ID: " + teacherId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
    }

    public static void main(String[] args) {
        Teacher t = new Teacher(1, "Seema", "Rai", "seema.rai@example.com");
        t.displayTeacherInfo();
    }
}
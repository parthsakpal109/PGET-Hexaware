package com.sis.model;
import java.util.*;

class Course {
    int courseId;
    String courseName;
    String courseCode;
    String instructorName;
    List<Enrollment> enrollments = new ArrayList<>();
    Teacher teacher;

    public Course(int courseId, String courseName, String courseCode, String instructorName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.instructorName = instructorName;
    }

    public void displayCourseInfo() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Course Name: " + courseName);
        System.out.println("Course Code: " + courseCode);
        System.out.println("Instructor: " + instructorName);
    }

    public static void main(String[] args) {
        Course c = new Course(101, "Java Programming", "JAVA101", "Mr. Sharma");
        c.displayCourseInfo();
    }
}

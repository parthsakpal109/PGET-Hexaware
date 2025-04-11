package com.hexaware.sis.entity;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int courseId;
    private String courseName;
    private String courseCode;
    private int credits;
    private Teacher instructor;
    private List<Enrollment> enrollments;

    // Default constructor
    public Course() {
        this.enrollments = new ArrayList<>();
    }

    //Parameterized Constructor without teacher
    public Course(int courseId, String courseName, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.enrollments = new ArrayList<>();
    }

    //Parameterized Constructor with CourseCode
    public Course(int courseId, String courseName, String courseCode, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.enrollments = new ArrayList<>();
    }

    //Parameterized Constructor with teacherId
    public Course(int courseId, String courseName, int credits, int teacherId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.enrollments = new ArrayList<>();
        if (teacherId > 0) {
            this.instructor = new Teacher();
            this.instructor.setTeacherId(teacherId);
        }
    }

    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Teacher getInstructor() {
        return instructor;
    }

    public void setInstructor(Teacher instructor) {
        this.instructor = instructor;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public int getTeacherId() {
        return (instructor != null) ? instructor.getTeacherId() : 0;
    }

    // Assign teacher to course
    public void assignTeacher(Teacher teacher) {
        this.instructor = teacher;
        teacher.addAssignedCourse(this);
    }

    // Update course info
    public void updateCourseInfo(String courseName, int credits) {
        this.courseName = courseName;
        this.credits = credits;
    }

    // Display course info
    public void displayCourseInfo() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Course Name: " + courseName);
        System.out.println("Course Code: " + courseCode); 
        System.out.println("Credits: " + credits);
        if (instructor != null)
            System.out.println("Instructor: " + instructor.getFirstName() + " " + instructor.getLastName());
        else
            System.out.println("Instructor: Not Assigned");
    }
}

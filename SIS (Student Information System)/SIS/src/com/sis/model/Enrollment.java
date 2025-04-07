package com.sis.model;

class Enrollment {
    int enrollmentId;
    Student student;
    Course course;
    String enrollmentDate;

    public Enrollment(int enrollmentId, Student student, Course course, String enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public static void main(String[] args) {
        Student s = new Student(2, "Priya", "Desai", "2002-11-15", "priya@example.com", "9812345678");
        Course c = new Course(102, "Database Systems", "DB102", "Dr. Mehta");
        Enrollment e = new Enrollment(1, s, c, "2025-04-07");
        System.out.println("Enrollment created for student: " + e.getStudent().firstName + " in course: " + e.getCourse().courseName);
    }
}
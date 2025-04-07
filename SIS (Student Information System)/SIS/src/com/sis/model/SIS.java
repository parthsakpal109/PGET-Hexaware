package com.sis.model;

import java.util.*;

public class SIS {

    List<Enrollment> enrollments;
    List<Payment> payments;

    public SIS() {
        enrollments = new ArrayList<>();
        payments = new ArrayList<>();
    }

    // Enroll a student in a course
    public void enrollStudentInCourse(Student student, Course course, String enrollmentDate) {
        for (Enrollment e : enrollments) {
            if (e.getStudent().equals(student) && e.getCourse().equals(course)) {
                System.out.println("Student already enrolled in this course.");
                return;
            }
        }

        Enrollment enrollment = new Enrollment(enrollments.size() + 1, student, course, enrollmentDate);
        enrollments.add(enrollment);
        student.enrollments.add(enrollment);
        course.enrollments.add(enrollment);
        System.out.println("Enrollment successful.");
    }

    // Assign a teacher to a course
    public void assignTeacherToCourse(Teacher teacher, Course course) {
        course.assignTeacher(teacher);
        System.out.println("Teacher assigned to course.");
    }

    // Record payment made by student
    public void recordPayment(Student student, double amount, String paymentDate) {
        if (amount <= 0) {
            System.out.println("Invalid payment amount.");
            return;
        }

        Payment payment = new Payment(payments.size() + 1, student, amount, paymentDate);
        payments.add(payment);
        student.payments.add(payment);
        System.out.println("Payment recorded successfully.");
    }

    // Generate enrollment report for a course
    public void generateEnrollmentReport(Course course) {
        System.out.println("Enrollment Report for: " + course.courseName);
        for (Enrollment e : course.enrollments) {
            System.out.println(" - " + e.getStudent().firstName + " " + e.getStudent().lastName);
        }
    }

    // Generate payment report for a student
    public void generatePaymentReport(Student student) {
        System.out.println("Payment Report for: " + student.firstName + " " + student.lastName);
        for (Payment p : student.payments) {
            System.out.println(" - Amount: " + p.getPaymentAmount() + ", Date: " + p.getPaymentDate());
        }
    }

    // Calculate course statistics (total enrollments and total payments)
    public void calculateCourseStatistics(Course course) {
        int count = course.enrollments.size();
        double total = 0;

        for (Enrollment e : course.enrollments) {
            for (Payment p : e.getStudent().payments) {
                total += p.getPaymentAmount();
            }
        }

        System.out.println("Course Stats for: " + course.courseName);
        System.out.println(" - Total Enrollments: " + count);
        System.out.println(" - Estimated Total Payments: " + total);
    }
}

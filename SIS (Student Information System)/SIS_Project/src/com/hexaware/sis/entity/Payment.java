package com.hexaware.sis.entity;

import java.time.LocalDate;

public class Payment {
    private int paymentId;
    private Student student;
    private double amount;
    private LocalDate paymentDate;

    // Default constructor
    public Payment() {}

    // Parameterized constructor
    public Payment(int paymentId, Student student, double amount, LocalDate paymentDate) {
        this.paymentId = paymentId;
        this.student = student;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    // Getters
    public int getPaymentId() {
        return paymentId;
    }

    public Student getStudent() {
        return student;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    // Setters
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    // Utility method (optional)
    public void displayPaymentInfo() {
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Student ID: " + (student != null ? student.getStudentId() : "N/A"));
        System.out.println("Amount Paid: $" + amount);
        System.out.println("Payment Date: " + paymentDate);
    }
}

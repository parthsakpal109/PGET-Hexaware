package com.sis.model;
import java.util.*;

class Payment {
    int paymentId;
    Student student;
    double amount;
    String paymentDate;

    public Payment(int paymentId, Student student, double amount, String paymentDate) {
        this.paymentId = paymentId;
        this.student = student;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public static void main(String[] args) {
        Student s = new Student(3, "Ravi", "Kumar", "2001-08-12", "ravi@example.com", "9123456780");
        Payment p = new Payment(1, s, 5000.00, "2025-04-07");
        System.out.println("Payment of " + p.getPaymentAmount() + " made on " + p.getPaymentDate());
    }
}


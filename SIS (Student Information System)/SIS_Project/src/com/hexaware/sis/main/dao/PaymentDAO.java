package com.hexaware.sis.main.dao;

import com.hexaware.sis.entity.Payment;
import com.hexaware.sis.entity.Student;
import com.hexaware.sis.exception.PaymentNotFoundException;
import com.hexaware.sis.main.util.DBConnUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class PaymentDAO {

    // Add a new payment record
    public void addPayment(Payment payment) {
        String sql = "INSERT INTO Payments (std_id, amt, payment_date) VALUES (?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, payment.getStudent().getStudentId());
            pstmt.setDouble(2, payment.getAmount());
            pstmt.setDate(3, Date.valueOf(payment.getPaymentDate()));

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        payment.setPaymentId(rs.getInt(1));
                    }
                }
                System.out.println("Payment recorded: $" + payment.getAmount() + " for Student ID " + payment.getStudent().getStudentId());
            }
        } catch (SQLException e) {
            throw new PaymentNotFoundException("Error adding payment: " + e.getMessage());
        }
    }

    //Retrieve all payments for a specific student
    public List<Payment> getPaymentsByStudentId(int studentId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM Payments WHERE std_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Payment payment = new Payment();
                    payment.setPaymentId(rs.getInt("payment_id"));
                    payment.setAmount(rs.getDouble("amt"));
                    payment.setPaymentDate(rs.getDate("payment_date").toLocalDate());

                    Student student = new Student();
                    student.setStudentId(studentId);
                    payment.setStudent(student);

                    payments.add(payment);
                }
            }
        } catch (SQLException e) {
            throw new PaymentNotFoundException("Error retrieving payments: " + e.getMessage());
        }

        if (payments.isEmpty()) {
            throw new PaymentNotFoundException("No payments found for Student ID: " + studentId);
        }
        return payments;
    }

    //Retrieve all payment records
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM Payments";
        try (Connection conn = DBConnUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setAmount(rs.getDouble("amt"));
                payment.setPaymentDate(rs.getDate("payment_date").toLocalDate());

                Student student = new Student();
                student.setStudentId(rs.getInt("std_id"));
                payment.setStudent(student);

                payments.add(payment);
            }
        } catch (SQLException e) {
            throw new PaymentNotFoundException("Error retrieving all payments: " + e.getMessage());
        }
        return payments;
    }

    //Modify an existing payment record
    public void updatePayment(int paymentId, double newAmount) {
        String sql = "UPDATE Payments SET amt = ? WHERE payment_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, newAmount);
            pstmt.setInt(2, paymentId);

            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new PaymentNotFoundException("Payment not found with ID: " + paymentId);
            }

            System.out.println("Payment updated: ID " + paymentId + ", New Amount: $" + newAmount);
        } catch (SQLException e) {
            throw new PaymentNotFoundException("Error updating payment: " + e.getMessage());
        }
    }

    //Remove a payment record
    public void deletePayment(int paymentId) {
        String sql = "DELETE FROM Payments WHERE payment_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, paymentId);
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new PaymentNotFoundException("Payment not found with ID: " + paymentId);
            }

            System.out.println("Payment deleted with ID: " + paymentId);
        } catch (SQLException e) {
            throw new PaymentNotFoundException("Error deleting payment: " + e.getMessage());
        }
    }
}

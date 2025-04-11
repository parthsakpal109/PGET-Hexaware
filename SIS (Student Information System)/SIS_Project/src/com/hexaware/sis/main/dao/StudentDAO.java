package com.hexaware.sis.main.dao;

import com.hexaware.sis.entity.Student;
import com.hexaware.sis.exception.StudentNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    //Add a new student to the database.
    public void addStudent(Student student) {
        if (exists(student.getStudentId())) {
            System.out.println("Student already exists with ID: " + student.getStudentId());
            return;
        }
        String sql = "INSERT INTO Students (std_id, first_nm, last_nm, dob, email, ph_nm) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = com.hexaware.sis.main.util.DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, student.getStudentId());
            pstmt.setString(2, student.getFirstName());
            pstmt.setString(3, student.getLastName());
            pstmt.setDate(4, Date.valueOf(student.getDateOfBirth()));
            pstmt.setString(5, student.getEmail());
            pstmt.setString(6, student.getPhoneNumber());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student added: " + student.getFirstName() + " " + student.getLastName());
            } else {
                System.out.println("Failed to add student with ID: " + student.getStudentId());
            }
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

 // Get list of all Students
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT std_id, first_nm, last_nm, dob, email, ph_nm FROM Students";
        try (Connection conn = com.hexaware.sis.main.util.DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("std_id");
                String firstName = rs.getString("first_nm");
                String lastName = rs.getString("last_nm");
                LocalDate dob = rs.getDate("dob").toLocalDate();
                String email = rs.getString("email");
                String phone = rs.getString("ph_nm");
                Student student = new Student(id, firstName, lastName, dob, email, phone);
                studentList.add(student);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all students: " + e.getMessage());
        }
        return studentList;
    }

    //Check if a student exists.
    private boolean exists(int studentId) {
        String sql = "SELECT std_id FROM Students WHERE std_id = ?";
        try (Connection conn = com.hexaware.sis.main.util.DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error checking student existence: " + e.getMessage());
            return false;
        }
    }

    //Retrieves a student by their ID.
    public Student getStudent(int studentId) {
        String sql = "SELECT std_id, first_nm, last_nm, dob, email, ph_nm FROM Students WHERE std_id = ?";
        try (Connection conn = com.hexaware.sis.main.util.DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("std_id");
                String firstName = rs.getString("first_nm");
                String lastName = rs.getString("last_nm");
                LocalDate dob = rs.getDate("dob").toLocalDate();
                String email = rs.getString("email");
                String phone = rs.getString("ph_nm");
                return new Student(id, firstName, lastName, dob, email, phone);
            } else {
                throw new StudentNotFoundException("Student not found with ID: " + studentId);
            }
        } catch (SQLException e) {
            throw new StudentNotFoundException("Error retrieving student: " + e.getMessage());
        }
    }

    //Update an existing student's information.
    public void updateStudent(Student student) {
        String sql = "UPDATE Students SET first_nm = ?, last_nm = ?, dob = ?, email = ?, ph_nm = ? WHERE std_id = ?";
        try (Connection conn = com.hexaware.sis.main.util.DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setDate(3, Date.valueOf(student.getDateOfBirth()));
            pstmt.setString(4, student.getEmail());
            pstmt.setString(5, student.getPhoneNumber());
            pstmt.setInt(6, student.getStudentId());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student updated: " + student.getFirstName() + " " + student.getLastName());
            } else {
                throw new StudentNotFoundException("Cannot update. Student not found with ID: " + student.getStudentId());
            }
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    // Removes a student from the database.
    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM Students WHERE std_id = ?";
        try (Connection conn = com.hexaware.sis.main.util.DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student removed with ID: " + studentId);
            } else {
                throw new StudentNotFoundException("Cannot delete. Student not found with ID: " + studentId);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }
}

package com.hexaware.sis.main.dao;

import com.hexaware.sis.entity.Enrollment;
import com.hexaware.sis.entity.Student;
import com.hexaware.sis.entity.Course;
import com.hexaware.sis.exception.InvalidEnrollmentDataException;
import com.hexaware.sis.main.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    // Add a new enrollment to the DB
    public void addEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO Enrollments (std_id, course_id, enrollment_date) VALUES (?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, enrollment.getStudent().getStudentId());
            pstmt.setInt(2, enrollment.getCourse().getCourseId());
            pstmt.setDate(3, Date.valueOf(enrollment.getEnrollmentDate()));
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        enrollment.setEnrollmentId(rs.getInt(1));
                    }
                }
                System.out.println("Enrollment added successfully.");
            }
        } catch (SQLException e) {
            throw new InvalidEnrollmentDataException("Error adding enrollment: " + e.getMessage());
        }
    }

    // Retrieve an enrollment by its ID
    public Enrollment getEnrollmentById(int enrollmentId) {
        String sql = "SELECT * FROM Enrollments WHERE enrollment_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, enrollmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int studentId = rs.getInt("std_id");
                    int courseId = rs.getInt("course_id");
                    Date enrollDate = rs.getDate("enrollment_date");

                    Student student = new Student();
                    student.setStudentId(studentId);
                    Course course = new Course();
                    course.setCourseId(courseId);

                    return new Enrollment(enrollmentId, student, course, enrollDate.toLocalDate());
                } else {
                    throw new InvalidEnrollmentDataException("Enrollment not found with ID: " + enrollmentId);
                }
            }
        } catch (SQLException e) {
            throw new InvalidEnrollmentDataException("Error fetching enrollment: " + e.getMessage());
        }
    }

    //Retrieve enrollments by course ID with full student info
    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT e.enrollment_id, e.enrollment_date, " +
                     "s.std_id, s.first_nm, s.last_nm " +
                     "FROM Enrollments e " +
                     "JOIN Students s ON e.std_id = s.std_id " +
                     "WHERE e.course_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, courseId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int enrollmentId = rs.getInt("enrollment_id");
                    Date enrollDate = rs.getDate("enrollment_date");

                    Student student = new Student();
                    student.setStudentId(rs.getInt("std_id"));
                    student.setFirstName(rs.getString("first_nm"));
                    student.setLastName(rs.getString("last_nm"));

                    Course course = new Course();
                    course.setCourseId(courseId);

                    list.add(new Enrollment(enrollmentId, student, course, enrollDate.toLocalDate()));
                }
            }
        } catch (SQLException e) {
            throw new InvalidEnrollmentDataException("Error retrieving enrollments: " + e.getMessage());
        }
        return list;
    }


    //Update an existing enrollment
    public void updateEnrollment(Enrollment enrollment) {
        String sql = "UPDATE Enrollments SET std_id = ?, course_id = ?, enrollment_date = ? WHERE enrollment_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, enrollment.getStudent().getStudentId());
            pstmt.setInt(2, enrollment.getCourse().getCourseId());
            pstmt.setDate(3, Date.valueOf(enrollment.getEnrollmentDate()));
            pstmt.setInt(4, enrollment.getEnrollmentId());

            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new InvalidEnrollmentDataException("No enrollment found with ID: " + enrollment.getEnrollmentId());
            }
            System.out.println("Enrollment updated.");
        } catch (SQLException e) {
            throw new InvalidEnrollmentDataException("Error updating enrollment: " + e.getMessage());
        }
    }

    //Delete an enrollment by its ID
    public void deleteEnrollment(int enrollmentId) {
        String sql = "DELETE FROM Enrollments WHERE enrollment_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, enrollmentId);
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new InvalidEnrollmentDataException("No enrollment found with ID: " + enrollmentId);
            }
            System.out.println("Enrollment deleted.");
        } catch (SQLException e) {
            throw new InvalidEnrollmentDataException("Error deleting enrollment: " + e.getMessage());
        }
    }

 //Delete all enrollments for a given student ID
    public void deleteEnrollmentsByStudentId(int studentId) {
        String sql = "DELETE FROM Enrollments WHERE std_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            int rows = pstmt.executeUpdate();
            System.out.println("Deleted " + rows + " enrollment(s) for student with ID: " + studentId);
        } catch (SQLException e) {
            throw new InvalidEnrollmentDataException("Error deleting enrollments for student ID: " + studentId + ": " + e.getMessage());
        }
    }

    // Returns all enrollments
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT * FROM Enrollments";
        try (Connection conn = DBConnUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int enrollmentId = rs.getInt("enrollment_id");
                int studentId = rs.getInt("std_id");
                int courseId = rs.getInt("course_id");
                Date enrollDate = rs.getDate("enrollment_date");

                Student student = new Student();
                student.setStudentId(studentId);
                Course course = new Course();
                course.setCourseId(courseId);

                list.add(new Enrollment(enrollmentId, student, course, enrollDate.toLocalDate()));
            }
        } catch (SQLException e) {
            throw new InvalidEnrollmentDataException("Error retrieving enrollments: " + e.getMessage());
        }
        return list;
    }
}

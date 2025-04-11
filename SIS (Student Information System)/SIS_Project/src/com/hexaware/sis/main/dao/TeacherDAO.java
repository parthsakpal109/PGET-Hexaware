package com.hexaware.sis.main.dao;

import com.hexaware.sis.entity.Teacher;
import com.hexaware.sis.exception.TeacherNotFoundException;
import com.hexaware.sis.main.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {

    //Add a new teacher to the database
    public void addTeacher(Teacher teacher) {
        String sql = "INSERT INTO Teacher (first_nm, last_nm, email) VALUES (?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, teacher.getFirstName());
            pstmt.setString(2, teacher.getLastName());
            pstmt.setString(3, teacher.getEmail());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        teacher.setTeacherId(rs.getInt(1));
                    }
                }
                System.out.println("Teacher added: " + teacher.getFirstName() + " " + teacher.getLastName());
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Teacher already exists with email: " + teacher.getEmail());
        } catch (SQLException e) {
            throw new TeacherNotFoundException("Error adding teacher: " + e.getMessage());
        }
    }

    //Retrieve a teacher by their ID
    public Teacher getTeacher(int teacherId) {
        String sql = "SELECT * FROM Teacher WHERE teacher_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, teacherId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setTeacherId(rs.getInt("teacher_id"));
                    teacher.setFirstName(rs.getString("first_nm"));
                    teacher.setLastName(rs.getString("last_nm"));
                    teacher.setEmail(rs.getString("email"));
                    return teacher;
                } else {
                    throw new TeacherNotFoundException("Teacher not found with ID: " + teacherId);
                }
            }
        } catch (SQLException e) {
            throw new TeacherNotFoundException("Error retrieving teacher: " + e.getMessage());
        }
    }

 //Update an existing teacher's information
    public void updateTeacher(Teacher teacher) {
        String sql = "UPDATE Teacher SET first_nm = ?, last_nm = ?, email = ? WHERE teacher_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, teacher.getFirstName());
            pstmt.setString(2, teacher.getLastName());
            pstmt.setString(3, teacher.getEmail());
            pstmt.setInt(4, teacher.getTeacherId());
            
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new TeacherNotFoundException("Cannot update. Teacher not found with ID: " + teacher.getTeacherId());
            }
            
            System.out.println("Teacher updated: " + teacher.getFirstName() + " " + teacher.getLastName());
        } catch (SQLException e) {
            throw new TeacherNotFoundException("Error updating teacher: " + e.getMessage());
        }
    }


    //Remove a teacher from the database
    public void deleteTeacher(int teacherId) {
        String sql = "DELETE FROM Teacher WHERE teacher_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, teacherId);
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new TeacherNotFoundException("Cannot delete. Teacher not found with ID: " + teacherId);
            }

            System.out.println("Teacher removed with ID: " + teacherId);
        } catch (SQLException e) {
            throw new TeacherNotFoundException("Error deleting teacher: " + e.getMessage());
        }
    }

    //Returns a list of all teachers
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM Teacher";
        try (Connection conn = DBConnUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(rs.getInt("teacher_id"));
                teacher.setFirstName(rs.getString("first_nm"));
                teacher.setLastName(rs.getString("last_nm"));
                teacher.setEmail(rs.getString("email"));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            throw new TeacherNotFoundException("Error retrieving teachers: " + e.getMessage());
        }
        return teachers;
    }
}

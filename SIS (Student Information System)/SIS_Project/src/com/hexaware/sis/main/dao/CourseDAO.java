package com.hexaware.sis.main.dao;

import com.hexaware.sis.entity.Course;
import com.hexaware.sis.exception.CourseNotFoundException;
import com.hexaware.sis.main.util.DBConnUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    //Add a new course to the database.
    public void addCourse(Course course) {
        String sql = "INSERT INTO Courses (course_nm, credits, teacher_id) VALUES (?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, course.getCourseName());
            pstmt.setInt(2, course.getCredits());
            
            if(course.getTeacherId() > 0) {
                pstmt.setInt(3, course.getTeacherId());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        course.setCourseId(generatedId);
                    }
                }
                System.out.println("Course added: " + course.getCourseName());
            } else {
                System.out.println("Failed to add course: " + course.getCourseName());
            }
        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }
    
    //Retrieves a course by its CourseCode / CourseId
    public Course getCourseByCode(String courseName) {
        String sql = "SELECT course_id, course_nm, credits, teacher_id FROM Courses WHERE LOWER(course_nm) = LOWER(?)";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, courseName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int courseId = rs.getInt("course_id");
                    String name = rs.getString("course_nm");
                    int credits = rs.getInt("credits");
                    int teacherId = rs.getInt("teacher_id");
                    return new Course(courseId, name, credits, teacherId);
                } else {
                    throw new CourseNotFoundException("Course not found with name: " + courseName);
                }
            }
        } catch (SQLException e) {
            throw new CourseNotFoundException("Error retrieving course: " + e.getMessage());
        }
    }
    
    // Retrieves a course by its ID
    public Course getCourseById(int courseId) {
        String sql = "SELECT course_id, course_nm, credits, teacher_id FROM Courses WHERE course_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, courseId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("course_nm");
                    int credits = rs.getInt("credits");
                    int teacherId = rs.getInt("teacher_id");
                    return new Course(courseId, name, credits, teacherId);
                } else {
                    throw new CourseNotFoundException("Course not found with ID: " + courseId);
                }
            }
        } catch (SQLException e) {
            throw new CourseNotFoundException("Error retrieving course: " + e.getMessage());
        }
    }
    
    // Update an existing course's information
    public void updateCourse(Course course) {
        String sql = "UPDATE Courses SET course_nm = ?, credits = ?, teacher_id = ? WHERE course_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, course.getCourseName());
            pstmt.setInt(2, course.getCredits());
            if(course.getTeacherId() > 0) {
                pstmt.setInt(3, course.getTeacherId());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            pstmt.setInt(4, course.getCourseId());
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Course updated: " + course.getCourseName());
            } else {
                throw new CourseNotFoundException("Cannot update. Course not found with ID: " + course.getCourseId());
            }
        } catch (SQLException e) {
            System.out.println("Error updating course: " + e.getMessage());
        }
    }

    
    // Removes a course from the database
    public void deleteCourse(int courseId) {
        String sql = "DELETE FROM Courses WHERE course_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, courseId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Course removed with ID: " + courseId);
            } else {
                throw new CourseNotFoundException("Cannot delete. Course not found with ID: " + courseId);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting course: " + e.getMessage());
        }
    }
    
    // Returns a list of all courses
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT course_id, course_nm, credits, teacher_id FROM Courses";
        try (Connection conn = DBConnUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                String name = rs.getString("course_nm");
                int credits = rs.getInt("credits");
                int teacherId = rs.getInt("teacher_id");
                courses.add(new Course(courseId, name, credits, teacherId));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all courses: " + e.getMessage());
        }
        return courses;
    }
}

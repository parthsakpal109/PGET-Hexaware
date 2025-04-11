package com.hexaware.sis.main;

import com.hexaware.sis.entity.*;
import com.hexaware.sis.exception.*;
import com.hexaware.sis.main.dao.*;
import com.hexaware.sis.main.util.DBPropertyUtil;
import com.hexaware.sis.main.util.DBConnUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
public class MainModule {
    
    static StudentDAO studentDAO = new StudentDAO();
    static CourseDAO courseDAO = new CourseDAO();
    static EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    static TeacherDAO teacherDAO = new TeacherDAO();
    static PaymentDAO paymentDAO = new PaymentDAO();

    public static void main(String[] args) {
        // Establish DataBase connection to ensure connectivity is working.
        Connection conn = null;
        try {
            conn = DBConnUtil.getConnection();
            System.out.println("Database connection established successfully.");
        } catch (SQLException e) {
            System.out.println("Error establishing database connection: " + e.getMessage());
            return;  
        }
        
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            //StartUp Menu
            System.out.println("\n=== Student Information System Menu ===");
            System.out.println("1. Add New Student");
            System.out.println("2. Enroll Student in a Course");
            System.out.println("3. Update Teacher Email");
            System.out.println("4. Delete Enrollment Record");
            System.out.println("5. Assign Teacher to Course");
            System.out.println("6. Delete Student and Their Enrollments");
            System.out.println("7. Update Payment Amount");
            System.out.println("8. Generate Total Payments for a Student");
            System.out.println("9. Generate Enrollment Report");
            System.out.println("10. Record Payment for a Student");
            System.out.println("11. Show All Students");
            System.out.println("12. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            try {
                switch(choice) {
                    case 1:
                        addNewStudent(scanner);
                        break;
                    case 2:
                        enrollStudentInCourse(scanner);
                        break;
                    case 3:
                        updateTeacherEmail(scanner);
                        break;
                    case 4:
                        deleteEnrollment(scanner);
                        break;
                    case 5:
                        assignTeacherToCourse(scanner);
                        break;
                    case 6:
                        deleteStudentAndEnrollments(scanner);
                        break;
                    case 7:
                        updatePaymentAmount(scanner);
                        break;
                    case 8:
                        generateTotalPaymentsForStudent(scanner);
                        break;
                    case 9:
                        generateEnrollmentReport(scanner);
                        break;
                    case 10:
                        recordPaymentForStudent(scanner);
                        break;
                    case 11:
                    	showAllStudents();
                        break;
                    case 12:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while(choice != 12);

        scanner.close();
    }

    // Add New Student to the database
    private static void addNewStudent(Scanner scanner) {
        System.out.println("Enter Student Details:");
        System.out.print("Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("First Name: ");
        String fName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lName = scanner.nextLine();
        System.out.print("Date of Birth (YYYY-MM-DD): ");
        LocalDate dob = LocalDate.parse(scanner.nextLine());
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();
        Student student = new Student(id, fName, lName, dob, email, phone);
        studentDAO.addStudent(student);
    }

    //Enroll a student in a course
    private static void enrollStudentInCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        Student student = studentDAO.getStudent(studentId);
        if (student == null) {
            throw new StudentNotFoundException("Student not found with ID: " + studentId);
        }
        
        System.out.print("Enter Course ID: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        Course course = null;
        try {
            course = courseDAO.getCourseById(courseId);
        } catch (CourseNotFoundException e) {
            System.out.println("Course not found with ID: " + courseId);
            return;
        }
        
        System.out.print("Enter Enrollment Date (YYYY-MM-DD): ");
        LocalDate enrollDate = LocalDate.parse(scanner.nextLine());
        student.enrollInCourse(course, enrollDate);
        
        Enrollment enrollment = new Enrollment(0, student, course, enrollDate);
        enrollmentDAO.addEnrollment(enrollment);
        
        System.out.println("Enrollment successful!");
    }


    //Update Teacher Email
    private static void updateTeacherEmail(Scanner scanner) {
        System.out.print("Enter Teacher ID: ");
        int teacherId = scanner.nextInt();
        scanner.nextLine();
        Teacher teacher = teacherDAO.getTeacher(teacherId);
        if (teacher == null) {
            throw new TeacherNotFoundException("Teacher not found with ID: " + teacherId);
        }

        System.out.print("Enter new Email: ");
        String newEmail = scanner.nextLine();

        teacher.updateTeacherInfo(teacher.getFirstName(), teacher.getLastName(), newEmail);

        teacherDAO.updateTeacher(teacher);

        System.out.println("Teacher email updated in the database.");
    }


    // Delete Enrollment Record
    private static void deleteEnrollment(Scanner scanner) {
        System.out.print("Enter Enrollment ID to delete: ");
        int enrollmentId = scanner.nextInt();
        scanner.nextLine();
        try {
            enrollmentDAO.deleteEnrollment(enrollmentId);
            System.out.println("Enrollment with ID " + enrollmentId + " deleted from the database.");
        } catch (InvalidEnrollmentDataException e) {
            System.out.println("Error deleting enrollment: " + e.getMessage());
        }
    }


 // Assign Teacher to Course
    private static void assignTeacherToCourse(Scanner scanner) {
        System.out.print("Enter Course Code / ID: ");
        String courseCode = scanner.nextLine();
        // Here we assume the user enters the course ID (numeric)
        Course course = courseDAO.getCourseById(Integer.parseInt(courseCode));
        if (course == null) {
            throw new CourseNotFoundException("Course not found with ID: " + courseCode);
        }
        System.out.print("Enter Teacher ID to assign: ");
        int teacherId = scanner.nextInt();
        scanner.nextLine();
        Teacher teacher = teacherDAO.getTeacher(teacherId);
        if (teacher == null) {
            throw new TeacherNotFoundException("Teacher not found with ID: " + teacherId);
        }
        course.assignTeacher(teacher);
      
        courseDAO.updateCourse(course);
        
        System.out.println("Teacher assigned to course successfully.");
    }


 //Delete a Student and all their Enrollments
    private static void deleteStudentAndEnrollments(Scanner scanner) {
        System.out.print("Enter Student ID to delete: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        try {
            enrollmentDAO.deleteEnrollmentsByStudentId(studentId);
            studentDAO.deleteStudent(studentId);
            System.out.println("Student and all associated enrollments have been deleted from the database.");
        } catch(Exception e) {
            System.out.println("Error deleting student and enrollments: " + e.getMessage());
        }
    }


 // Update Payment Amount for a Payment Record
    private static void updatePaymentAmount(Scanner scanner) {
        System.out.print("Enter Payment ID to update: ");
        int paymentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new Payment Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        paymentDAO.updatePayment(paymentId, amount);
    }


    // Generate Total Payments for a Student
    private static void generateTotalPaymentsForStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        Student student = studentDAO.getStudent(studentId);
        if (student == null) {
            throw new StudentNotFoundException("Student not found with ID: " + studentId);
        }
        List<Payment> payments = paymentDAO.getPaymentsByStudentId(studentId);
        double total = payments.stream().mapToDouble(Payment::getAmount).sum();
        System.out.println("Total payments made by Student ID " + studentId + " is: $" + total);
    }


    // Generate Enrollment Report for a Course
    private static void generateEnrollmentReport(Scanner scanner) {
        System.out.print("Enter Course ID for Enrollment Report: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentsByCourse(courseId);
        System.out.println("Enrollment Report for Course ID " + courseId + ":");
        for(Enrollment e : enrollments) {
            System.out.println("Enrollment ID: " + e.getEnrollmentId() + ", Student: " +
                e.getStudent().getFirstName() + " " + e.getStudent().getLastName() +
                ", Date: " + e.getEnrollmentDate());
        }
    }

    // Record a Payment for a Student
    private static void recordPaymentForStudent(Scanner scanner) {
        System.out.print("Enter Student ID for Payment: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        Student student = studentDAO.getStudent(studentId);
        if (student == null) {
            throw new StudentNotFoundException("Student not found with ID: " + studentId);
        }
        System.out.print("Enter Payment Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Payment Date (YYYY-MM-DD): ");
        LocalDate paymentDate = LocalDate.parse(scanner.nextLine());
        student.makePayment(amount, paymentDate);
        Payment payment = new Payment(student.getPaymentHistory().size(), student, amount, paymentDate);
        paymentDAO.addPayment(payment);
    }
    
    //Show all Available Students
    private static void showAllStudents() {
        List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found in the system.");
            return;
        }

        System.out.println("\nList of All Students:");

        System.out.println("ID\tFirst Name\tLast Name\tDOB\t\tEmail\t\t\tPhone");

        for (Student s : students) {
            System.out.println(
                s.getStudentId() + "\t" +
                s.getFirstName() + "\t\t" +
                s.getLastName() + "\t\t" +
                s.getDateOfBirth() + "\t" +
                s.getEmail() + "\t" +
                s.getPhoneNumber()
            );
        }
    }

}

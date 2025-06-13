package packagetest3.com.hexa.hibernateEX;

import java.util.Scanner;
import Dao.StudentDao;
import Model.Student;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDao dao = new StudentDao();

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Save Student");
            System.out.println("2. Remove Student (HQL)");
            System.out.println("3. Remove Student (Native SQL)");
            System.out.println("4. Update Student");
            System.out.println("5. Search by Roll Number");
            System.out.println("6. Show All Students (Native)");
            System.out.println("7. Search by Name");
            System.out.println("8. Search by Name and Marks");
            System.out.println("9. Show Students with Marks > X");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No: ");
                    int r = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();
                    dao.saveData(new Student(r, name, marks));
                    break;

                case 2:
                    System.out.print("Enter Roll No to remove (HQL): ");
                    int roll1 = sc.nextInt();
                    dao.removeByRollNo(roll1);
                    break;

                case 3:
                    System.out.print("Enter Roll No to remove (Native): ");
                    int roll2 = sc.nextInt();
                    dao.removeDataN(roll2);
                    break;

                case 4:
                    System.out.print("Enter Roll No to update: ");
                    int roll3 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new marks: ");
                    double newMarks = sc.nextDouble();
                    dao.updateData(roll3, newName, newMarks);
                    break;

                case 5:
                    System.out.print("Enter Roll No to search: ");
                    int roll4 = sc.nextInt();
                    Student s = dao.searchData(roll4);
                    if (s != null) {
                        System.out.println(s);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 6:
                    dao.showDataN();
                    break;

                case 7:
                    System.out.print("Enter Name to search: ");
                    sc.nextLine();
                    String searchName = sc.nextLine();
                    dao.searchByName(searchName);
                    break;

                case 8:
                    System.out.print("Enter Name: ");
                    sc.nextLine();
                    String n = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double m = sc.nextDouble();
                    dao.searchByNameAndMarks(n, m);
                    break;

                case 9:
                    System.out.print("Enter Marks to find students with higher marks: ");
                    double minMarks = sc.nextDouble();
                    dao.searchHigherMark(minMarks);
                    break;

                case 10:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

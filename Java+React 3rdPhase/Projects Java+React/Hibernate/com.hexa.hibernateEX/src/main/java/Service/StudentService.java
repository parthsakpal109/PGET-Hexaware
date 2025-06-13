package Service;

import java.util.Scanner;
import Dao.StudentDao;
import Model.Student;

public class StudentService {
    Student s;
    StudentDao dao;
    Scanner sc;

    public StudentService() {
        s = new Student();
        sc = new Scanner(System.in);
        dao = new StudentDao();
    }

    public void saveData() {
        System.out.print("Enter Roll Number: ");
        s.setRollno(sc.nextInt());
        sc.nextLine();

        System.out.print("Enter Name: ");
        s.setName(sc.nextLine());

        System.out.print("Enter Marks: ");
        s.setMarks(sc.nextDouble());
        sc.nextLine();

        dao.saveData(s);
    }

    public void removeData() {
        System.out.print("Enter Roll Number to Remove: ");
        int rollno = sc.nextInt();
        sc.nextLine();

        dao.removeData(rollno);
    }

    public void updateData() {
        System.out.print("Enter Existing Roll Number: ");
        int oldRoll = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Roll Number: ");
        int newRoll = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Marks: ");
        double newMarks = sc.nextDouble();
        sc.nextLine();

        dao.updateData(oldRoll, newRoll, newMarks);
    }

    public void searchData() {
        System.out.print("Enter Roll Number to Search: ");
        int rollno = sc.nextInt();
        sc.nextLine();

        Student student = dao.searchData(rollno);
        if (student != null) {
            System.out.println("Student Found: " + student);
        } else {
            System.out.println("Student Not Found.");
        }
    }

    public void searchByName() {
        System.out.print("Enter Name to Search: ");
        String name = sc.nextLine();
        dao.searchByName(name);
    }

    public void showData() {
        dao.showData();
    }
}

package Service;

import java.util.Scanner;
import Dao.StudentDao;
import Model.Student;

public class StudentService {
    StudentDao dao = new StudentDao();
    Scanner sc = new Scanner(System.in);

    public void saveStudent() {
        Student s = new Student();
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
        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        dao.removeData(roll);
    }

    public void updateData() {
        System.out.print("Enter Roll Number to update: ");
        int roll = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new Name: ");
        String name = sc.nextLine();

        System.out.print("Enter new Marks: ");
        double marks = sc.nextDouble();

        dao.updateData(roll, name, marks);
    }

    public void searchData() {
        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        Student s = dao.searchData(roll);
        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println("No student found with Roll Number: " + roll);
        }
    }

    public void showData() {
        dao.showData();
    }

    public void searchByName() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        dao.searchByName(name);
    }

    public void searchByNameAndMarks() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();
        sc.nextLine();

        dao.searchByNameAndMarks(name, marks);
    }

    public void showHigherMarks() {
        System.out.print("Enter threshold marks: ");
        double marks = sc.nextDouble();
        dao.searchHigherMark(marks);
    }
}

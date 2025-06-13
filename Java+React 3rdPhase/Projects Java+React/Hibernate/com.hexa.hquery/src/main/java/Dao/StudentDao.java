package Dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Connection.StudentConn;
import Model.Student;

public class StudentDao implements DaoStudentI {

    public void saveData(Student s) {
        Session session = StudentConn.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(s);
        tx.commit();
        session.close();
    }

    public void removeData(int rno) {
        Session session = StudentConn.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Student s = session.get(Student.class, rno);
        if (s != null) {
            session.delete(s);
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student not found.");
        }
        tx.commit();
        session.close();
    }

    public void updateData(int oldRoll, int newRoll, double newMarks) {
        Session session = StudentConn.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Student s = session.get(Student.class, oldRoll);
        if (s != null) {
            s.setRollno(newRoll);
            s.setMarks(newMarks);
            session.update(s);
            System.out.println("Student updated.");
        } else {
            System.out.println("Student not found.");
        }
        tx.commit();
        session.close();
    }

    public Student searchData(int rno) {
        Session session = StudentConn.getSessionFactory().openSession();
        Student s = session.get(Student.class, rno);
        session.close();
        return s;
    }

    public void searchByName(String name) {
        Session session = StudentConn.getSessionFactory().openSession();
        String hql = "FROM Student WHERE name = :studentName";
        List<Student> students = session.createQuery(hql, Student.class)
                                        .setParameter("studentName", name)
                                        .list();
        session.close();

        if (students.isEmpty()) {
            System.out.println("No student found with name: " + name);
        } else {
            System.out.println("Students Found:");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public void showData() {
        Session session = StudentConn.getSessionFactory().openSession();
        List<Student> students = session.createQuery("from Student", Student.class).list();
        for (Student s : students) {
            System.out.println(s);
        }
        session.close();
    }
}

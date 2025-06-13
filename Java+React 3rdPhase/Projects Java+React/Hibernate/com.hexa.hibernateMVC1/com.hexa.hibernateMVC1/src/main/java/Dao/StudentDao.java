package Dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Connection.StudentConn;
import Model.Student;

public class StudentDao implements DaoStudentI {
    SessionFactory factory;

    public StudentDao() {
        factory = StudentConn.getSessionFactory();
    }

    public void saveData(Student s) {
        Session session = factory.openSession();
        Transaction txt = session.beginTransaction();
        session.save(s);
        txt.commit();
    }

    public void removeData(int rollno) {
        Session session = factory.openSession();
        Transaction txt = session.beginTransaction();
        Student s = session.find(Student.class, rollno);
        if (s != null) {
            session.delete(s);
        } else {
            System.out.println("Roll number '" + rollno + "' not found");
        }
        txt.commit();
    }

    public void removeByRollNo(int rollno) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Query<?> q = session.createQuery("delete from Student where rollno = :rollno");
        q.setParameter("rollno", rollno);
        int r = q.executeUpdate();
        if (r > 0) {
            System.out.println("Removed");
        } else {
            System.out.println("Not Found");
        }
        tx.commit();
    }

    public void updateData(int rollno, String name, double marks) {
        Session session = factory.openSession();
        Transaction txt = session.beginTransaction();
        Student s = session.find(Student.class, rollno);
        if (s != null) {
            s.setName(name);
            s.setMarks(marks);
        } else {
            System.out.println("Roll number '" + rollno + "' not found");
        }
        txt.commit();
    }

    public Student searchData(int rollno) {
        Session session = factory.openSession();
        Transaction txt = session.beginTransaction();
        Student s = session.find(Student.class, rollno);
        txt.commit();
        return s;
    }

    public void showData() {
        Session session = factory.openSession();
        Query<Student> query = session.createQuery("from Student", Student.class);
        List<Student> students = query.list();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void searchByName(String name) {
        Session session = factory.openSession();
        Query<Student> query = session.createQuery("from Student where name = :name", Student.class);
        query.setParameter("name", name);
        List<Student> students = query.list();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void searchByNameAndMarks(String name, double marks) {
        Session session = factory.openSession();
        Query<Student> query = session.createQuery("from Student where name = :name and marks = :marks", Student.class);
        query.setParameter("name", name);
        query.setParameter("marks", marks);
        List<Student> students = query.list();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void searchHigherMark(double marks) {
        Session session = factory.openSession();
        Query<Student> query = session.createQuery("from Student", Student.class);
        List<Student> allStudents = query.list();

        List<Student> filtered = allStudents.stream()
                .filter(s -> s.getMarks() > marks)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No student has marks greater than " + marks);
        } else {
            filtered.forEach(System.out::println);
        }
    }
}

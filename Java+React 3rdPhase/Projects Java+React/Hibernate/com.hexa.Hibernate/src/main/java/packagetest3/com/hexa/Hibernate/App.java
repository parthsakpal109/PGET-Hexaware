package packagetest3.com.hexa.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App {
    
    SessionFactory sessionFactory;  // interface

    public App() {
        sessionFactory = new Configuration().configure("hiber.config.xml").addAnnotatedClass(Student.class).buildSessionFactory();
    }

    void insert() {
        Session session = sessionFactory.openSession();
        Transaction txTransaction = session.beginTransaction();
        
        Student s = new Student();
        s.setRollno(101);
        s.setNameString("Parth");
        s.setMarks(999.0);
        
        session.save(s);
        txTransaction.commit();
        session.close(); 
    }

    void search(int rollno) {
        Session session = sessionFactory.openSession();
        Student rs = session.get(Student.class, rollno);
        if (rs != null) {
            System.out.println(rs.toString());
        } else {
            System.out.println("Not Found");
        }
        session.close();
    }

    void searchByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction txTransaction = session.beginTransaction();
        
        // Use HQL to query by name
        String hql = "FROM Student s WHERE s.nameString = :name";
        Student sr = session.createQuery(hql, Student.class)
                            .setParameter("name", name)
                            .uniqueResult();
        
        if (sr != null) {
            System.out.println(sr.toString());
        } else {
            System.out.println("Not Found");
        }
        
        txTransaction.commit();
        session.close(); 
    }

    void removeByRollNo(int rno) {
        Session session = sessionFactory.openSession();
        Transaction txTransaction = session.beginTransaction();
        
        Student r = session.find(Student.class, rno);
        
        if (r == null) {
            System.out.println("Not Found");
        } else {
            session.delete(r);
            System.out.println("Deleted Successfully");
        }
        
        txTransaction.commit();
        session.close();
    }

    void updateNameFee(int roll, double marks, String name) {
        Session session = sessionFactory.openSession();
        Transaction txTransaction = session.beginTransaction();
        
        // Fetch existing student first
        Student s = session.get(Student.class, roll);
        if (s != null) {
            s.setNameString(name);
            s.setMarks(marks);
            
            session.update(s);
            System.out.println("Updated Successfully");
        } else {
            System.out.println("Student with roll no " + roll + " Not Found");
        }
        
        txTransaction.commit();
        session.close();
    }

    public static void main(String[] args) {
        App obj = new App();
        
        obj.insert();
        System.out.println("Hello World!");

        obj.search(101);

        obj.searchByName("Parth");

        obj.removeByRollNo(101);

        obj.search(101);
        
        obj.insert();
        obj.updateNameFee(101, 888.8, "UpdatedParth");
        
        obj.search(101);
    }
}

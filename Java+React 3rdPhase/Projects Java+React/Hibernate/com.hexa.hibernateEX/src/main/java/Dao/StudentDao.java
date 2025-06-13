package Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
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
        Transaction tx = session.beginTransaction();
        session.save(s);
        tx.commit();
    }

    public void removeData(int rollno) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Student s = session.find(Student.class, rollno);
        if (s != null) {
            session.delete(s);
        } else {
            System.out.println("Roll number '" + rollno + "' not found");
        }
        tx.commit();
    }

    public void removeByRollNo(int rollno) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("delete from Student where rollno = :rollno");
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
        Transaction tx = session.beginTransaction();
        Student s = session.find(Student.class, rollno);
        if (s != null) {
            s.setName(name);
            s.setMarks(marks);
        } else {
            System.out.println("Roll number '" + rollno + "' not found");
        }
        tx.commit();
    }

    public Student searchData(int rollno) {
        Session session = factory.openSession();
        Student s = session.find(Student.class, rollno);
        return s;
    }

    public void ShowData() {
        Session session = factory.openSession();
        NativeQuery query = session.createNativeQuery("SELECT * FROM Student", Student.class);
        List students = query.list();
        for (int i = 0; i < students.size(); i++) {
            Student s = (Student) students.get(i);
            System.out.println(s);
        }
    }

    public void searchByName(String name) {
        Session session = factory.openSession();
        Query query = session.createQuery("from Student where name = :name");
        query.setParameter("name", name);
        List list = query.list();
        for (int i = 0; i < list.size(); i++) {
            Student s = (Student) list.get(i);
            System.out.println(s);
        }
    }

    public void searchByNameAndMarks(String name, double marks) {
        Session session = factory.openSession();
        Query query = session.createQuery("from Student where name = :name and marks = :marks");
        query.setParameter("name", name);
        query.setParameter("marks", marks);
        List list = query.list();
        for (int i = 0; i < list.size(); i++) {
            Student s = (Student) list.get(i);
            System.out.println(s);
        }
    }

    public void searchHigherMark(double marks) {
        Session session = factory.openSession();
        Query query = session.createQuery("from Student");
        List list = query.list();
        List result = new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            Student s = (Student) list.get(i);
            if (s.getMarks() > marks) {
                result.add(s);
            }
        }

        if (result.size() == 0) {
            System.out.println("No student has marks greater than " + marks);
        } else {
            for (int i = 0; i < result.size(); i++) {
                Student s = (Student) result.get(i);
                System.out.println(s);
            }
        }
    }


public void showDataN() {
		Session session = factory.openSession();
		NativeQuery<Student> query = session.createNativeQuery("select * from Student", Student.class);

		List<Student> students=	 query.list();
		 
		for(Student s : students )	
		{
			System.out.println(s.toString());
			
		}
	}

	public void removeDataN(int rollno) {
		Session session = factory.openSession();
		Transaction txt = session.beginTransaction();
		NativeQuery<Student> query = session.createNativeQuery("delete from Student where rollno = :rollno", Student.class);
		query.setParameter("rollno", rollno);
		
		int status = query.executeUpdate();
		txt.commit();
		
		if (status > 0) {
			System.out.println("Updated");
		} else {
			System.out.println("Not Found");
		}
	}

	public void updateData(int oldRoll, int newRoll, double newMarks) {
		// TODO Auto-generated method stub
		
	}

	public void showData() {
		// TODO Auto-generated method stub
		
	}

		
}

package Connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import Model.Student;

public class StudentConn {
    private static SessionFactory sessionFactory;

    StudentConn() {
        sessionFactory = new Configuration()
            .configure("hiber.config.xml")
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        new StudentConn();
        return sessionFactory;
    }
}

package packagetest3.SpringFrame2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext con = new ClassPathXmlApplicationContext("beans.xml");

        Employee s1 = (Employee) con.getBean("s1");
        Employee s2 = (Employee) con.getBean("s2");
        Employee s3 = (Employee) con.getBean("s3");

        System.out.println(s1);
        System.out.println(s1.getAddress());

        System.out.println(s2);
        System.out.println(s2.getAddress());

        System.out.println(s3);
        System.out.println(s3.getAddress());
    }
}


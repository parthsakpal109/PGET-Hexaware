package packagetest2.SpringFramework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main( String[] args )
	{
		ApplicationContext con = new ClassPathXmlApplicationContext("beans.xml");		  
		  Student s1=(Student)con.getBean("s1");
		  Student s2=(Student)con.getBean("s2");
		  System.out.println(s1.toString());
		  System.out.println(s2.toString());
	}
}


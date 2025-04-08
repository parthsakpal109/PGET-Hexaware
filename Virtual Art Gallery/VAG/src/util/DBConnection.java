package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = PropertyUtil.getPropertyString();

                Properties props = new Properties();
                InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
                props.load(input);

                String username = props.getProperty("username");
                String password = props.getProperty("password");

                connection = DriverManager.getConnection(url, username, password);
                System.out.println("✅ Connected to DB successfully!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

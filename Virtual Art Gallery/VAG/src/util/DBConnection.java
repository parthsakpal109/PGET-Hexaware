package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    private static Connection connection;

    // Establish and return the database connection
    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = PropertyUtil.getPropertyString();  // Get JDBC URL from PropertyUtil

                Properties props = new Properties();
                InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
                if (input == null) {
                    throw new SQLException("Unable to find db.properties file.");
                }
                props.load(input);

                // Retrieve username and password from db.properties
                String username = props.getProperty("username");
                String password = props.getProperty("password");

                // Establish the connection to the database
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("✅ Connected to DB successfully!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

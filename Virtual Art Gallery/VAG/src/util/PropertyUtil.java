package util;

import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    // Returns the JDBC connection URL
    public static String getPropertyString() {
        try {
            Properties props = new Properties();
            // Load properties file from the resources folder
            InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream("db.properties");
            if (input == null) {
                throw new RuntimeException("Unable to find db.properties file.");
            }
            props.load(input);

            // Read database connection details from the properties file
            String host = props.getProperty("hostname");
            String port = props.getProperty("port");
            String db = props.getProperty("dbname");

            // Build and return the JDBC URL
            return "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

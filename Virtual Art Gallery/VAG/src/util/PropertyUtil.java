package util;

import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    public static String getPropertyString() {
        try {
            Properties props = new Properties();
            InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream("db.properties");
            props.load(input);

            String host = props.getProperty("hostname");
            String port = props.getProperty("port");
            String db = props.getProperty("dbname");

            return "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

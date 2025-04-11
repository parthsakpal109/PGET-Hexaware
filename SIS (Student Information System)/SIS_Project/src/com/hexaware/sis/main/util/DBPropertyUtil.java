package com.hexaware.sis.main.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {
    //Database properties file path
    private static final String DEFAULT_FILE = "com/hexaware/sis/main/util/dbconfig.properties";
    //Load the file 
    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = DBPropertyUtil.class.getClassLoader().getResourceAsStream(DEFAULT_FILE)) {
            if (input == null) {
                throw new IOException("Unable to find " + DEFAULT_FILE + " in classpath.");
            }
            //extract resources
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Error loading database properties: " + e.getMessage());
        }
        return properties;
    }
    //extract the DB URL
    public static String getConnectionString() {
        Properties properties = loadProperties();
        return properties.getProperty("db.url");
    }

    //Extract the DB Username
    public static String getUsername() {
        Properties properties = loadProperties();
        return properties.getProperty("db.username");
    }

    //Extarct the DB Password
    public static String getPassword() {
        Properties properties = loadProperties();
        return properties.getProperty("db.password");
    }
}

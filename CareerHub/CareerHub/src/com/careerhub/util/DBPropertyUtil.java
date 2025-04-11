package com.careerhub.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getConnectionString(String propertyFileName) {
        String connectionString = null;
        try (FileInputStream fis = new FileInputStream(propertyFileName)) {
            Properties props = new Properties();
            props.load(fis);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String pass = props.getProperty("db.password");

            connectionString = url + "?user=" + user + "&password=" + pass;

        } catch (IOException e) {
            System.out.println("Error reading database properties: " + e.getMessage());
        }

        return connectionString;
    }
}

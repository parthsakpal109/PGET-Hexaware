package com.careerhub.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    private static final String PROP_FILE = "db.properties";

    public static Connection getConnection() {
        Connection con = null;
        try {
            String connStr = DBPropertyUtil.getConnectionString(PROP_FILE);
            con = DriverManager.getConnection(connStr);
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return con;
    }
}

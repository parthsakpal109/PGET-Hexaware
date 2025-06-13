package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.customer;
import util.ConnectionHelper;

public class customerDaoI implements customerDao {

    static Connection conn;
    PreparedStatement stmt;

    static {
        try {
            conn = ConnectionHelper.getConnection();
        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }

    public boolean exists(int userId) {
        try {
            String sql = "SELECT userId FROM customer WHERE userId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Existence check error: " + e.getMessage());
        }
        return false;
    }

    public void addUser(customer c) {
        if (exists(c.getuserID())) {
            System.out.println("Customer with userId " + c.getuserID() + " already exists.");
            return;
        }
        try {
            String sql = "INSERT INTO customer (userId, name, email, password) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, c.getuserID());
            stmt.setString(2, c.getName());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getPassword());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Sign-up successful!");
            } else {
                System.out.println("Sign-up failed.");
            }
        } catch (Exception e) {
            System.out.println("Insert error: " + e.getMessage());
        }
    }

    public void signIn(int userId, String password) {
        try {
            String sql = "SELECT name FROM customer WHERE userId = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Welcome, " + rs.getString("name") + "!");
            } else {
                System.out.println("Invalid userId or password.");
            }
        } catch (Exception e) {
            System.out.println("Sign-in error: " + e.getMessage());
        }
    }

    public void forgotPassword(int userId, String email) {
        try {
            String sql = "SELECT password FROM customer WHERE userId = ? AND email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Your password is: " + rs.getString("password"));
            } else {
                System.out.println("No matching user found.");
            }
        } catch (Exception e) {
            System.out.println("Forgot password error: " + e.getMessage());
        }
    }

    public void listAllUsers() {
        try {
            String sql = "SELECT userId, name FROM customer";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Registered Users:");
            while (rs.next()) {
                System.out.println("UserID: " + rs.getInt("userId") + ", Name: " + rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("List error: " + e.getMessage());
        }
    }

	public void addCustomer(customer c) {
		
	}
}

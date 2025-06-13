package assignment2.BankSystemJavaReact;

import java.util.Scanner;
import dao.customerDaoI;
import model.customer;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        customerDaoI dao = new customerDaoI();

        while (true) {
            System.out.println("\n=== Login Management System ===");
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Forgot Password");
            System.out.println("4. List All Users");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = sc.nextInt();
            sc.nextLine();  

            switch (choice) {
                case 1:
                    System.out.println("\n--- Sign Up ---");
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    customer c = new customer();
                    dao.addUser(c);
                    break;

                case 2:
                    System.out.println("\n--- Sign In ---");
                    System.out.print("Enter User ID: ");
                    int loginId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Password: ");
                    String loginPass = sc.nextLine();
                    dao.signIn(loginId, loginPass);
                    break;

                case 3:
                    System.out.println("\n--- Forgot Password ---");
                    System.out.print("Enter User ID: ");
                    int forgotId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Email: ");
                    String forgotEmail = sc.nextLine();
                    dao.forgotPassword(forgotId, forgotEmail);
                    break;

                case 4:
                    System.out.println("\n--- List of All Users ---");
                    dao.listAllUsers();
                    break;

                case 5:
                    System.out.println("Exiting. Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

package com.hexa.hibernateMVC.com.hexa.hibernateMVC1;

import java.util.Scanner;
import Service.StudentService;

public class App {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Save Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Student");
            System.out.println("4. Search by Roll");
            System.out.println("5. Show All Students");
            System.out.println("6. Search by Name");
            System.out.println("7. Search by Name and Marks");
            System.out.println("8. Show Students with Higher Marks");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    service.saveStudent();
                    break;
                case 2:
                    service.removeData();
                    break;
                case 3:
                    service.updateData();
                    break;
                case 4:
                    service.searchData();
                    break;
                case 5:
                    service.showData();
                    break;
                case 6:
                    service.searchByName();
                    break;
                case 7:
                    service.searchByNameAndMarks();
                    break;
                case 8:
                    service.showHigherMarks();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 9);
        sc.close();
    }
}

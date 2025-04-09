package com.careerhub.main;

import com.careerhub.dao.DatabaseManagerImpl;
import com.careerhub.entity.*;
import java.util.*;
import java.io.*;

public class MainModule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DatabaseManagerImpl db = new DatabaseManagerImpl();
        int choice;

        do {
            System.out.println("\n====== CareerHub Job Board ======");
            System.out.println("1. Add Company");
            System.out.println("2. Add Applicant");
            System.out.println("3. Post Job Listing");
            System.out.println("4. View All Companies");
            System.out.println("5. View All Applicants");
            System.out.println("6. View All Job Listings");
            System.out.println("7. Apply for a Job");
            System.out.println("8. View Applications for a Job");
            System.out.println("9. Calculate Average Salary");
            System.out.println("10. Upload Resume (File Check)");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Company ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Company Name: ");
                    String name = sc.nextLine();
                    System.out.print("Location: ");
                    String loc = sc.nextLine();
                    Company comp = new Company(id, name, loc);
                    db.insertCompany(comp);
                }
                case 2 -> {
                    System.out.print("Applicant ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("First Name: ");
                    String fn = sc.nextLine();
                    System.out.print("Last Name: ");
                    String ln = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Resume Path: ");
                    String resume = sc.nextLine();
                    Applicant ap = new Applicant(id, fn, ln, email, phone, resume);
                    db.insertApplicant(ap);
                }
                case 3 -> {
                    System.out.print("Job ID: ");
                    int jobId = sc.nextInt(); sc.nextLine();
                    System.out.print("Company ID: ");
                    int companyId = sc.nextInt(); sc.nextLine();
                    System.out.print("Job Title: ");
                    String title = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Location: ");
                    String loc = sc.nextLine();
                    System.out.print("Salary: ");
                    double salary = sc.nextDouble(); sc.nextLine();
                    System.out.print("Job Type: ");
                    String type = sc.nextLine();
                    System.out.print("Posted Date (YYYY-MM-DD): ");
                    String date = sc.nextLine();
                    JobListing job = new JobListing(jobId, companyId, title, desc, loc, salary, type, date);
                    db.insertJobListing(job);
                }
                case 4 -> db.getCompanies().forEach(System.out::println);
                case 5 -> db.getApplicants().forEach(System.out::println);
                case 6 -> db.getJobListings().forEach(System.out::println);
                case 7 -> {
                    System.out.print("Job ID: ");
                    int jobId = sc.nextInt(); sc.nextLine();
                    System.out.print("Applicant ID: ");
                    int appId = sc.nextInt(); sc.nextLine();
                    System.out.print("Cover Letter: ");
                    String cover = sc.nextLine();
                    db.applyForJob(appId, jobId, cover);
                }
                case 8 -> {
                    System.out.print("Enter Job ID to view applications: ");
                    int jobId = sc.nextInt(); sc.nextLine();
                    db.getApplicationsForJob(jobId).forEach(System.out::println);
                }
                case 9 -> {
                    double avg = db.calculateAverageSalary();
                    System.out.printf("Average Salary Offered: %.2f\n", avg);
                }
                case 10 -> {
                    System.out.print("Enter resume file path to upload: ");
                    String path = sc.nextLine();
                    db.uploadResume(path);
                }
                case 0 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}

package com.careerhub.dao;

import com.careerhub.entity.*;
import com.careerhub.myexceptions.*;
import com.careerhub.util.DBConnection;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DatabaseManagerImpl implements IDatabaseManager {
    private Connection con;

    public DatabaseManagerImpl() {
        con = DBConnection.getConnection();
    }

    @Override
    public void initializeDatabase() {
        System.out.println("Database initialized (dummy implementation).");
    }

    @Override
    public void insertCompany(Company company) {
        String sql = "INSERT INTO Companies (CompanyID, CompanyName, Location) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, company.getCompanyID());
            ps.setString(2, company.getCompanyName());
            ps.setString(3, company.getLocation());
            int rows = ps.executeUpdate();
            System.out.println(rows + " company record inserted.");
        } catch (SQLException e) {
            System.out.println("Error inserting company: " + e.getMessage());
        }
    }

    @Override
    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT * FROM Companies";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                companies.add(new Company(
                    rs.getInt("CompanyID"),
                    rs.getString("CompanyName"),
                    rs.getString("Location")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving companies: " + e.getMessage());
        }
        return companies;
    }

    @Override
    public void insertApplicant(Applicant applicant) {
        String sql = "INSERT INTO Applicants (ApplicantID, FirstName, LastName, Email, Phone, Resume) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, applicant.getApplicantID());
            ps.setString(2, applicant.getFirstName());
            ps.setString(3, applicant.getLastName());
            ps.setString(4, applicant.getEmail());
            ps.setString(5, applicant.getPhone());
            ps.setString(6, applicant.getResume());
            int rows = ps.executeUpdate();
            System.out.println(rows + " applicant record inserted.");
        } catch (SQLException e) {
            System.out.println("Error inserting applicant: " + e.getMessage());
        }
    }

    @Override
    public List<Applicant> getApplicants() {
        List<Applicant> applicants = new ArrayList<>();
        String sql = "SELECT * FROM Applicants";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                applicants.add(new Applicant(
                    rs.getInt("ApplicantID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
                    rs.getString("Phone"),
                    rs.getString("Resume")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving applicants: " + e.getMessage());
        }
        return applicants;
    }

    @Override
    public void insertJobListing(JobListing job) {
        String sql = "INSERT INTO JobListings (JobID, CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, job.getJobID());
            ps.setInt(2, job.getCompanyID());
            ps.setString(3, job.getJobTitle());
            ps.setString(4, job.getJobDescription());
            ps.setString(5, job.getJobLocation());
            ps.setDouble(6, job.getSalary());
            ps.setString(7, job.getJobType());
            ps.setString(8, job.getPostedDate());
            int rows = ps.executeUpdate();
            System.out.println(rows + " job listing inserted.");
        } catch (SQLException e) {
            System.out.println("Error inserting job listing: " + e.getMessage());
        }
    }

    @Override
    public List<JobListing> getJobListings() {
        List<JobListing> jobs = new ArrayList<>();
        String sql = "SELECT * FROM JobListings";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                jobs.add(new JobListing(
                    rs.getInt("JobID"),
                    rs.getInt("CompanyID"),
                    rs.getString("JobTitle"),
                    rs.getString("JobDescription"),
                    rs.getString("JobLocation"),
                    rs.getDouble("Salary"),
                    rs.getString("JobType"),
                    rs.getString("PostedDate")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving job listings: " + e.getMessage());
        }
        return jobs;
    }

    @Override
    public void applyForJob(int jobID, int applicantID, String coverLetter) {
        String sql = "INSERT INTO JobApplications (JobID, ApplicantID, ApplicationDate, CoverLetter) VALUES (?, ?, NOW(), ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, jobID);
            ps.setInt(2, applicantID);
            ps.setString(3, coverLetter);
            int rows = ps.executeUpdate();
            System.out.println(rows + " application submitted.");
        } catch (SQLException e) {
            System.out.println("Error applying for job: " + e.getMessage());
        }
    }

    @Override
    public List<JobApplication> getApplicationsForJob(int jobID) {
        List<JobApplication> apps = new ArrayList<>();
        String sql = "SELECT * FROM JobApplications WHERE JobID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, jobID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                apps.add(new JobApplication(
                    rs.getInt("ApplicationID"),
                    rs.getInt("JobID"),
                    rs.getInt("ApplicantID"),
                    rs.getString("ApplicationDate"),
                    rs.getString("CoverLetter")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving applications: " + e.getMessage());
        }
        return apps;
    }

    @Override
    public double calculateAverageSalary() {
        double totalSalary = 0;
        int count = 0;
        String sql = "SELECT Salary FROM JobListings";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                double salary = rs.getDouble("Salary");
                if (salary < 0) {
                    throw new InvalidSalaryException("Salary cannot be negative.");
                }
                totalSalary += salary;
                count++;
            }
            if (count == 0) return 0.0;
            return totalSalary / count;
        } catch (InvalidSalaryException | SQLException e) {
            System.out.println("Error calculating average salary: " + e.getMessage());
        }
        return 0.0;
    }

    @Override
    public void uploadResume(String filepath) {
        File file = new File(filepath);
        try (FileInputStream fis = new FileInputStream(file)) {
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + filepath);
            }
            if (file.length() > 1048576) { // 1MB max
                throw new IOException("File size exceeded the limit.");
            }
            if (!filepath.endsWith(".pdf") && !filepath.endsWith(".docx")) {
                throw new IOException("Unsupported file format.");
            }
            System.out.println("Resume uploaded successfully: " + filepath);
        } catch (IOException e) {
            System.out.println("Resume upload error: " + e.getMessage());
        }
    }

    @Override
    public void insertJobApplication(JobApplication application) {
        String sql = "INSERT INTO JobApplications (JobID, ApplicantID, ApplicationDate, CoverLetter) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, application.getJobID());
            ps.setInt(2, application.getApplicantID());
            ps.setString(3, application.getApplicationDate());
            ps.setString(4, application.getCoverLetter());
            int rows = ps.executeUpdate();
            System.out.println(rows + " job application inserted.");
        } catch (SQLException e) {
            System.out.println("Error inserting job application: " + e.getMessage());
        }
    }
}

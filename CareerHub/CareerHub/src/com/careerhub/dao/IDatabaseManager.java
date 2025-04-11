package com.careerhub.dao;

import com.careerhub.entity.*;

import java.util.List;

public interface IDatabaseManager {
    void insertCompany(Company company);
    List<Company> getCompanies();

    void insertApplicant(Applicant applicant);
    List<Applicant> getApplicants();

    void insertJobListing(JobListing job);
    List<JobListing> getJobListings();

    void applyForJob(int jobID, int applicantID, String coverLetter);
    List<JobApplication> getApplicationsForJob(int jobID);

    double calculateAverageSalary();
    void uploadResume(String filepath);
    
    void initializeDatabase();
    void insertJobApplication(JobApplication application);

}

package com.careerhub.entity;

public class Company {
    private int companyID;
    private String companyName;
    private String location;

    // Default constructor
    public Company() {}

    // Parameterized constructor
    public Company(int companyID, String companyName, String location) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.location = location;
    }

    // Getters and Setters
    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Company [companyID=" + companyID + ", companyName=" + companyName + ", location=" + location + "]";
    }
}

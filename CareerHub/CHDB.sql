CREATE DATABASE IF NOT EXISTS CareerHub;
USE CareerHub;

CREATE TABLE IF NOT EXISTS Companies (
    CompanyID INT PRIMARY KEY AUTO_INCREMENT,
    CompanyName VARCHAR(255) NOT NULL UNIQUE,
    Location VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Jobs (
    JobID INT PRIMARY KEY AUTO_INCREMENT,
    CompanyID INT,
    JobTitle VARCHAR(255) NOT NULL,
    JobDescription TEXT NOT NULL,
    JobLocation VARCHAR(255) NOT NULL,
    Salary DECIMAL(10,2) CHECK (Salary >= 0),
    JobType ENUM('Full-time', 'Part-time', 'Contract') NOT NULL,
    PostedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CompanyID) REFERENCES Companies(CompanyID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Applicants (
    ApplicantID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(100) NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    Phone VARCHAR(15) UNIQUE NOT NULL,
    Resume TEXT,
    ExperienceYears INT CHECK (ExperienceYears >= 0) -- Added experience column
);

CREATE TABLE IF NOT EXISTS Applications (
    ApplicationID INT PRIMARY KEY AUTO_INCREMENT,
    JobID INT,
    ApplicantID INT,
    ApplicationDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    CoverLetter TEXT,
    FOREIGN KEY (JobID) REFERENCES Jobs(JobID) ON DELETE CASCADE,
    FOREIGN KEY (ApplicantID) REFERENCES Applicants(ApplicantID) ON DELETE CASCADE
);

INSERT INTO Companies (CompanyName, Location) VALUES
('Tata Consultancy Services', 'Mumbai'),
('Infosys', 'Bengaluru'),
('Wipro', 'Hyderabad'),
('HCL Technologies', 'Noida'),
('Tech Mahindra', 'Pune');

INSERT INTO Jobs (CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType) VALUES
(1, 'Software Engineer', 'Develop and maintain software solutions.', 'Mumbai', 900000, 'Full-time'),
(2, 'Data Analyst', 'Analyze data and generate insights.', 'Bengaluru', 800000, 'Full-time'),
(3, 'System Administrator', 'Manage IT infrastructure and networks.', 'Hyderabad', 750000, 'Full-time'),
(4, 'Project Manager', 'Lead teams and manage projects.', 'Noida', 1200000, 'Full-time'),
(5, 'UI/UX Designer', 'Design user-friendly interfaces.', 'Pune', 700000, 'Full-time');

INSERT INTO Applicants (FirstName, LastName, Email, Phone, Resume, ExperienceYears) VALUES
('Amit', 'Sharma', 'amit.sharma@example.com', '9876543210', 'Experienced Java Developer', 5),
('Neha', 'Verma', 'neha.verma@example.com', '9876543211', 'Data Analyst with 3 years of experience', 3),
('Rajesh', 'Kumar', 'rajesh.kumar@example.com', '9876543212', 'System Administrator with expertise in cloud computing', 6),
('Pooja', 'Reddy', 'pooja.reddy@example.com', '9876543213', 'Project Manager with Agile certification', 8),
('Vikram', 'Patel', 'vikram.patel@example.com', '9876543214', 'UI/UX Designer with strong portfolio', 4);

INSERT INTO Applications (JobID, ApplicantID, CoverLetter) VALUES
(1, 1, 'I am excited to apply for this role as a software engineer.'),
(2, 2, 'My expertise in data analytics makes me a great fit for this role.'),
(3, 3, 'I have hands-on experience in managing IT infrastructure.'),
(4, 4, 'I have successfully led multiple software development projects.'),
(5, 5, 'My UI/UX design skills align well with this role.');

SELECT j.JobTitle, COUNT(a.ApplicationID) AS ApplicationCount
FROM Jobs j
LEFT JOIN Applications a ON j.JobID = a.JobID
GROUP BY j.JobTitle;

SELECT j.JobTitle, c.CompanyName, j.JobLocation, j.Salary
FROM Jobs j
JOIN Companies c ON j.CompanyID = c.CompanyID
WHERE j.Salary BETWEEN 800000 AND 900000;

SELECT j.JobTitle, c.CompanyName, a.ApplicationDate
FROM Applications a
JOIN Jobs j ON a.JobID = j.JobID
JOIN Companies c ON j.CompanyID = c.CompanyID
WHERE a.ApplicantID = 1;

SELECT AVG(Salary) AS AverageSalary
FROM Jobs
WHERE Salary > 0;

SELECT c.CompanyName, COUNT(j.JobID) AS JobCount
FROM Jobs j
JOIN Companies c ON j.CompanyID = c.CompanyID
GROUP BY c.CompanyName
HAVING COUNT(j.JobID) = (
    SELECT MAX(JobCount) FROM (
        SELECT COUNT(JobID) AS JobCount FROM Jobs GROUP BY CompanyID
    ) AS JobCounts
);

SELECT DISTINCT a.FirstName, a.LastName, a.Email, a.Phone
FROM Applications app
JOIN Jobs j ON app.JobID = j.JobID
JOIN Companies c ON j.CompanyID = c.CompanyID
JOIN Applicants a ON app.ApplicantID = a.ApplicantID
WHERE c.Location = 'Pune' AND a.ExperienceYears >= 3;

SELECT DISTINCT JobTitle 
FROM Jobs 
WHERE Salary BETWEEN 60000 AND 80000;

SELECT j.JobID, j.JobTitle 
FROM Jobs j
LEFT JOIN Applications a ON j.JobID = a.JobID
WHERE a.ApplicationID IS NULL;

SELECT a.FirstName, a.LastName, c.CompanyName, j.JobTitle 
FROM Applications app
JOIN Applicants a ON app.ApplicantID = a.ApplicantID
JOIN Jobs j ON app.JobID = j.JobID
JOIN Companies c ON j.CompanyID = c.CompanyID;

SELECT c.CompanyName, COUNT(j.JobID) AS JobCount
FROM Companies c
LEFT JOIN Jobs j ON c.CompanyID = j.CompanyID
GROUP BY c.CompanyName;

SELECT a.FirstName, a.LastName, COALESCE(c.CompanyName, 'No Application') AS CompanyName, COALESCE(j.JobTitle, 'No Job Applied') AS JobTitle
FROM Applicants a
LEFT JOIN Applications app ON a.ApplicantID = app.ApplicantID
LEFT JOIN Jobs j ON app.JobID = j.JobID
LEFT JOIN Companies c ON j.CompanyID = c.CompanyID;

SELECT DISTINCT c.CompanyName 
FROM Companies c
JOIN Jobs j ON c.CompanyID = j.CompanyID
WHERE j.Salary > (SELECT AVG(Salary) FROM Jobs WHERE Salary > 0);

ALTER TABLE Applicants ADD COLUMN City VARCHAR(100);
SELECT FirstName, LastName, CONCAT(City, ', India') AS CityState
FROM Applicants;

SELECT * 
FROM Jobs 
WHERE JobTitle LIKE 'Developer' OR JobTitle LIKE 'Engineer';

SELECT a.FirstName, a.LastName, COALESCE(j.JobTitle, 'No Job Applied') AS JobTitle
FROM Applicants a
LEFT JOIN Applications app ON a.ApplicantID = app.ApplicantID
LEFT JOIN Jobs j ON app.JobID = j.JobID

UNION

SELECT 'No Applicant' AS FirstName, 'No Applicant' AS LastName, j.JobTitle
FROM Jobs j
LEFT JOIN Applications app ON j.JobID = app.JobID
WHERE app.ApplicationID IS NULL;

SELECT a.FirstName, a.LastName, c.CompanyName
FROM Applicants a
CROSS JOIN Companies c
WHERE c.Location = 'Chennai' AND a.ExperienceYears > 2;
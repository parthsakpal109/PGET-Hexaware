CREATE DATABASE SISDB;
USE SISDB;

CREATE TABLE Students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15) UNIQUE NOT NULL
);
SHOW TABLES;

CREATE TABLE Teacher (
    teacher_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE Courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    credits INT NOT NULL CHECK (credits > 0),
    teacher_id INT,
    FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id) ON DELETE SET NULL
);

CREATE TABLE Enrollments (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    enrollment_date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES Courses(course_id) ON DELETE CASCADE
);

CREATE TABLE Payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    amount DECIMAL(10,2) NOT NULL CHECK (amount > 0),
    payment_date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Students(student_id) ON DELETE CASCADE
);

INSERT INTO Students (first_name, last_name, date_of_birth, email, phone_number) VALUES
('Arya', 'Sahu', '2003-05-14', 'aryasahu@gmail.com', '9876543210'),
('Parth', 'Sakpal', '2003-03-05', 'parthsakpal2003@gmail.com', '8446639223'),
('Amit', 'Patel', '2000-03-18', 'amit.patel@gmail.com', '9876543212'),
('Nikhil', 'Singh', '2002-10-10', 'nikhilsingh@gmail.com', '9876543213'),
('Vikram', 'Singh', '2003-12-30', 'vikram.singh@gmail.com', '9876543214'),
('Nisha', 'Yadav', '2003-01-25', 'nisha.yadav@gmail.com', '9876543215'),
('Ramesh', 'Gupta', '2003-11-05', 'ramesh.gupta@gmail.com', '9876543216'),
('Komal', 'Mehta', '2002-06-20', 'komal.mehta@gmail.com', '9876543217'),
('Anil', 'Kumar', '2002-09-15', 'anil.kumar@gmail.com', '9876543218'),
('Pooja', 'Chopra', '2003-04-11', 'pooja.chopra@gmail.com', '9876543219');

INSERT INTO Teacher (first_name, last_name, email) VALUES
('Dr. Rama', 'Krishna', 'ramakrishna23@gmail.com'),
('Dr. Sunita', 'Karad', 'sunita.karad@yahoo.co.in'),
('Prof. Namrata', 'Naikwade', 'namratanaikwade31@email.com');

INSERT INTO Courses (course_name, credits, teacher_id) VALUES
('Mathematics', 4, 1),
('Physics', 3, 2),
('Chemistry', 3, 2),
('Computer Science', 4, 3),
('English Literature', 2, NULL);

INSERT INTO Enrollments (student_id, course_id, enrollment_date) VALUES
(1, 1, '2025-01-10'),
(2, 2, '2025-01-12'),
(3, 3, '2025-01-14'),
(4, 4, '2025-01-16'),
(5, 1, '2025-01-18'),
(6, 2, '2025-01-20'),
(7, 3, '2025-01-22'),
(8, 4, '2025-01-24'),
(9, 1, '2025-01-26'),
(10, 2, '2025-01-28');

INSERT INTO Payments (student_id, amount, payment_date) VALUES
(1, 5000.00, '2025-02-01'),
(2, 4500.00, '2025-02-02'),
(3, 4800.00, '2025-02-03'),
(4, 5200.00, '2025-02-04'),
(5, 5000.00, '2025-02-05'),
(6, 4500.00, '2025-02-06'),
(7, 4800.00, '2025-02-07'),
(8, 5200.00, '2025-02-08'),
(9, 5000.00, '2025-02-09'),
(10, 4500.00, '2025-02-10');
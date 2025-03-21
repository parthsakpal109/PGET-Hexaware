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

INSERT INTO Courses (course_id, course_name, credits, teacher_id) VALUES
(1, 'Mathematics', 4, 1),
(2, 'Physics', 3, 2),
(3, 'Chemistry', 3, 2),
(4, 'Computer Science', 4, 3),
(5, 'English Literature', 2, NULL);

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

SELECT * FROM Enrollments where course_id=3 ;  

INSERT INTO Students (first_name, last_name, date_of_birth, email, phone_number) VALUES
('John', 'Doe', '1995-08-15', 'john.doe@example.com', '1234567890');

INSERT INTO Enrollments (student_id, course_id, enrollment_date) VALUES
(11, 4, '2025-01-10');

UPDATE Teacher SET email = 'namrata123@gmail.com'  
WHERE teacher_id = 3;alter

DELETE FROM Enrollments WHERE course_id=3; 

UPDATE Courses SET teacher_id = 1 WHERE course_id = 5;

DELETE FROM Students WHERE student_id = 3;

UPDATE Payments SET amount = 5500.00  WHERE payment_id = 5;

SELECT s.student_id, s.first_name, s.last_name, SUM(p.amount) AS total_payments
FROM Students s
JOIN Payments p ON s.student_id = p.student_id
WHERE s.student_id = 1
GROUP BY s.student_id, s.first_name, s.last_name;

SELECT c.course_id, c.course_name, COUNT(e.student_id) AS student_count
FROM Courses c
LEFT JOIN Enrollments e ON c.course_id = e.course_id
GROUP BY c.course_id, c.course_name
ORDER BY student_count DESC;


SELECT s.first_name, s.last_name, c.course_name
FROM Students s
JOIN Enrollments e ON s.student_id = e.student_id
JOIN Courses c ON e.course_id = c.course_id
ORDER BY s.first_name, s.last_name;

SELECT t.first_name, t.last_name, c.course_name
FROM Teacher t
JOIN Courses c ON t.teacher_id = c.teacher_id
ORDER BY t.first_name, t.last_name;

SELECT s.first_name, s.last_name, e.enrollment_date, c.course_name
FROM Students s
JOIN Enrollments e ON s.student_id = e.student_id
JOIN Courses c ON e.course_id = c.course_id
WHERE c.course_name = 'Physics'
ORDER BY e.enrollment_date;

SELECT s.student_id, s.first_name, s.last_name
FROM Students s
LEFT JOIN Payments p ON s.student_id = p.student_id
WHERE p.payment_id IS NULL;

SELECT c.course_id, c.course_name
FROM Courses c
LEFT JOIN Enrollments e ON c.course_id = e.course_id
WHERE e.enrollment_id IS NULL;

SELECT DISTINCT s.student_id, s.first_name, s.last_name
FROM Students s
JOIN Enrollments e1 ON s.student_id = e1.student_id
JOIN Enrollments e2 ON s.student_id = e2.student_id AND e1.course_id <> e2.course_id
ORDER BY s.first_name, s.last_name;

SELECT t.teacher_id, t.first_name, t.last_name
FROM Teacher t
LEFT JOIN Courses c ON t.teacher_id = c.teacher_id
WHERE c.course_id IS NULL;

SELECT AVG(student_count) AS avg_students_per_course
FROM (
    SELECT course_id, COUNT(student_id) AS student_count
    FROM Enrollments
    GROUP BY course_id
) AS course_enrollments;

SELECT s.student_id, s.first_name, s.last_name, p.amount
FROM Students s
JOIN Payments p ON s.student_id = p.student_id
WHERE p.amount = (SELECT MAX(amount) FROM Payments);

SELECT c.course_id, c.course_name, COUNT(e.student_id) AS enrollment_count
FROM Courses c
JOIN Enrollments e ON c.course_id = e.course_id
GROUP BY c.course_id
HAVING COUNT(e.student_id) = (
    SELECT MAX(enrollment_count) 
    FROM (SELECT COUNT(student_id) AS enrollment_count FROM Enrollments GROUP BY course_id) AS subquery
);

SELECT t.teacher_id, t.first_name, t.last_name, COALESCE(SUM(p.amount), 0) AS total_payments
FROM Teacher t
LEFT JOIN Courses c ON t.teacher_id = c.teacher_id
LEFT JOIN Enrollments e ON c.course_id = e.course_id
LEFT JOIN Payments p ON e.student_id = p.student_id
GROUP BY t.teacher_id;

SELECT s.student_id, s.first_name, s.last_name
FROM Students s
JOIN Enrollments e ON s.student_id = e.student_id
GROUP BY s.student_id
HAVING COUNT(DISTINCT e.course_id) = (SELECT COUNT(*) FROM Courses);

SELECT first_name, last_name 
FROM Teacher 
WHERE teacher_id NOT IN (SELECT DISTINCT teacher_id FROM Courses WHERE teacher_id IS NOT NULL);

SELECT AVG(YEAR(CURDATE()) - YEAR(date_of_birth)) AS average_age FROM Students;

SELECT course_id, course_name 
FROM Courses 
WHERE course_id NOT IN (SELECT DISTINCT course_id FROM Enrollments);

SELECT s.student_id, s.first_name, s.last_name, c.course_name, SUM(p.amount) AS total_payment
FROM Students s
JOIN Enrollments e ON s.student_id = e.student_id
JOIN Courses c ON e.course_id = c.course_id
JOIN Payments p ON s.student_id = p.student_id
GROUP BY s.student_id, c.course_name;

SELECT s.student_id, s.first_name, s.last_name, COUNT(p.payment_id) AS payment_count
FROM Students s
JOIN Payments p ON s.student_id = p.student_id
GROUP BY s.student_id
HAVING COUNT(p.payment_id) > 1;

SELECT s.student_id, s.first_name, s.last_name, SUM(p.amount) AS total_payments
FROM Students s
JOIN Payments p ON s.student_id = p.student_id
GROUP BY s.student_id;

SELECT c.course_id, c.course_name, COUNT(e.student_id) AS enrollment_count
FROM Courses c
LEFT JOIN Enrollments e ON c.course_id = e.course_id
GROUP BY c.course_id;

SELECT AVG(p.amount) AS average_payment FROM Payments p;

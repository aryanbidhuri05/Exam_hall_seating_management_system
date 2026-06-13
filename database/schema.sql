CREATE DATABASE IF NOT EXISTS intelliseat;
USE intelliseat;

-- =========================
-- STUDENT TABLE
-- =========================
CREATE TABLE IF NOT EXISTS students (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
roll_number VARCHAR(30) NOT NULL UNIQUE,
department VARCHAR(60) NOT NULL,
semester INT NOT NULL
);

-- =========================
-- EXAM TABLE
-- =========================
CREATE TABLE IF NOT EXISTS exams (
id INT PRIMARY KEY AUTO_INCREMENT,
exam_name VARCHAR(120) NOT NULL,
subject_name VARCHAR(120) NOT NULL,
department VARCHAR(60) NOT NULL,
semester INT NOT NULL,
exam_date VARCHAR(20) NOT NULL
);

-- =========================
-- SHIFT TABLE
-- =========================
CREATE TABLE IF NOT EXISTS shifts (
id INT PRIMARY KEY AUTO_INCREMENT,
shift_name VARCHAR(80) NOT NULL,
start_time VARCHAR(10) NOT NULL,
end_time VARCHAR(10) NOT NULL
);

-- =========================
-- SEATING TABLE
-- =========================
CREATE TABLE IF NOT EXISTS seating (
id INT PRIMARY KEY AUTO_INCREMENT,
student_name VARCHAR(100) NOT NULL,
roll_number VARCHAR(30) NOT NULL,
department VARCHAR(60) NOT NULL,
semester INT NOT NULL,
exam_name VARCHAR(120) NOT NULL,
hall_number VARCHAR(30) NOT NULL,
seat_number VARCHAR(30) NOT NULL
);

-- =========================
-- DEFAULT STUDENT DATA
-- =========================

INSERT INTO students (name, roll_number, department, semester) VALUES
('Aarav Sharma', 'CSE001', 'Computer Science', 1),
('Ananya Verma', 'CSE002', 'Computer Science', 1),
('Rohan Mehta', 'CSE003', 'Computer Science', 2),
('Priya Singh', 'CSE004', 'Computer Science', 2),
('Aditya Kapoor', 'CSE005', 'Computer Science', 3),
('Sneha Joshi', 'CSE006', 'Computer Science', 3),
('Rahul Malhotra', 'CSE007', 'Computer Science', 4),
('Neha Gupta', 'CSE008', 'Computer Science', 4),
('Karan Arora', 'CSE009', 'Computer Science', 5),
('Simran Kaur', 'CSE010', 'Computer Science', 5),

('Arjun Nair', 'IT011', 'Information Technology', 1),
('Pooja Bansal', 'IT012', 'Information Technology', 1),
('Yash Khanna', 'IT013', 'Information Technology', 2),
('Meera Iyer', 'IT014', 'Information Technology', 2),
('Dev Patel', 'IT015', 'Information Technology', 3),
('Ishita Roy', 'IT016', 'Information Technology', 3),
('Varun Sethi', 'IT017', 'Information Technology', 4),
('Nikita Rao', 'IT018', 'Information Technology', 4),
('Harsh Vardhan', 'IT019', 'Information Technology', 5),
('Ritika Das', 'IT020', 'Information Technology', 5),

('Aman Tiwari', 'ECE021', 'Electronics', 1),
('Shreya Jain', 'ECE022', 'Electronics', 1),
('Vikas Chauhan', 'ECE023', 'Electronics', 2),
('Muskan Ali', 'ECE024', 'Electronics', 2),
('Sahil Bhatia', 'ECE025', 'Electronics', 3),
('Tanvi Saxena', 'ECE026', 'Electronics', 3),
('Mohit Rana', 'ECE027', 'Electronics', 4),
('Kriti Mishra', 'ECE028', 'Electronics', 4),
('Rajat Yadav', 'ECE029', 'Electronics', 5),
('Payal Sharma', 'ECE030', 'Electronics', 5),

('Aakash Pandey', 'ME031', 'Mechanical', 1),
('Divya Reddy', 'ME032', 'Mechanical', 1),
('Sandeep Kumar', 'ME033', 'Mechanical', 2),
('Khushi Agarwal', 'ME034', 'Mechanical', 2),
('Ritesh Thakur', 'ME035', 'Mechanical', 3),
('Komal Sinha', 'ME036', 'Mechanical', 3),
('Abhishek Rawat', 'ME037', 'Mechanical', 4),
('Tanya Chawla', 'ME038', 'Mechanical', 4),
('Nitin Grover', 'ME039', 'Mechanical', 5),
('Sakshi Anand', 'ME040', 'Mechanical', 5);

-- Continue similarly until 100 students are inserted
-- using different departments and semesters.

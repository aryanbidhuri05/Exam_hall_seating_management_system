# Exam_hall_seating_management_system
Project Overview

IntelliSeat is a Java-based desktop application developed to automate and simplify the process of exam hall seating arrangement in educational institutions. The project eliminates manual seat allocation and provides an efficient, organized, and user-friendly solution for managing students, exams, shifts, and seating generation.

The system is built using Java as the backend language, JavaFX for the graphical user interface, JDBC for database connectivity, and MySQL as the database management system.

The project is designed to reduce human effort, improve seating allocation efficiency, and minimize the possibility of cheating during examinations by ensuring students from the same department are not seated adjacent to each other.

Technologies Used

Technology	Purpose
Java	Backend Logic
JavaFX	Frontend User Interface
JDBC	Database Connectivity
MySQL	Database Management
CSS	Styling and Theme Design
FXML	JavaFX Layout Design
Features of IntelliSeat

1. Secure Login System

Interactive login page
Username and password authentication
Error alerts for invalid credentials
Logout functionality
2. Student Management Module

Add student details
Update student records
Delete student records
Search students
Display students in table format
Student Information Includes:

Student Name
Roll Number
Department
Semester
3. Exam Management Module

Add exam details
Assign subjects
Manage semesters and departments
Store exam schedules
Exam Information Includes:

Exam Name
Subject Name
Department
Semester
Exam Date
4. Shift Management Module

Create multiple exam shifts
Assign start and end timings
Manage shift scheduling
5. Automatic Seating Arrangement Generation

Automatically allocates seats
Generates hall-wise seating plans
Prevents adjacent seating of same department students
Automatically moves to next hall if current hall is full
Seating Details Include:

Student Name
Roll Number
Department
Semester
Exam Name
Hall Number
Seat Number
6. Export Feature

Users can download generated seating arrangements in:

PDF format
CSV format
7. Refresh Functionality

Reloads updated database records instantly
Dynamically updates table data
User Interface Design

The project follows a modern and professional colour scheme:

Component	Colour
Background	Blue
Main Colour	Navy Blue
Accent Buttons	Royal Blue
Secondary Background	Grey
Text Colour	Dark Grey
Font Used

Times New Roman
Project Structure

IntelliSeat/
│
├── src/
│   ├── controller/
│   ├── dao/
│   ├── model/
│   ├── ui/
│   └── util/
│
├── resources/
│   ├── css/
│   └── fxml/
│
├── database/
│   └── schema.sql
│
└── README.md
Module Description

Controller Package

Contains JavaFX controller classes responsible for handling UI events and interactions.

DAO Package

Contains database operation classes used for inserting, updating, deleting, and retrieving data using JDBC.

Model Package

Contains model classes representing entities such as Student, Exam, Shift, and Seating.

UI Package

Contains the main JavaFX application launcher.

Util Package

Contains utility classes including database connection management.

Database Schema

The project uses MySQL with the following tables:

students
exams
shifts
seating
The database is initialized using:

schema.sql
Default Data

The project includes:

100 default student records
Indian student names
Roll numbers
Departments
Semesters
This data is preloaded for testing and demonstration purposes.

Software Requirements

Required Software

Java JDK 17 or above
JavaFX SDK
MySQL Server
MySQL Workbench
Eclipse / MyEclipse / IntelliJ IDEA
How to Run the Project

Step 1: Setup Database

Open MySQL Workbench and run:

CREATE DATABASE intelliseat;
USE intelliseat;
Then execute:

schema.sql
Step 2: Configure JDBC

Open:

DBConnection.java
Update:

String url = "jdbc:mysql://localhost:3306/intelliseat";
String username = "root";
String password = "yourpassword";
Step 3: Configure JavaFX

Add JavaFX SDK libraries to the project.

VM Arguments:

--module-path "PATH_TO_JAVAFX_LIB" --add-modules javafx.controls,javafx.fxml
Example:

--module-path "C:\openjfx-21.0.11_windows-x64_bin-sdk\javafx-sdk-21.0.11\lib" --add-modules javafx.controls,javafx.fxml
Step 4: Run the Project

Run:

MainApp.java
using:

Run As → Java Application
Key Concepts Used

The project demonstrates implementation of:

Object-Oriented Programming
JavaFX GUI Development
JDBC Connectivity
MySQL Database Integration
File Handling
Exception Handling
Event Handling
Collections Framework
Modular Programming
Future Enhancements

Possible future improvements:

Admin and Faculty Roles
Email Notifications
Online Exam Hall Allocation
AI-based Smart Seating
Attendance Integration
Dark Mode UI
Cloud Database Support
Project Outcome

The IntelliSeat system successfully automates exam seating arrangement and management processes, reducing manual work and improving efficiency in educational institutions.

The project provides a practical demonstration of Java desktop application development integrated with database management and modern user interface design.

Developed By

Kritika Dhiman B.Tech Student Graphic Era Hill University

CREATE DATABASE HOSPITAL;
USE HOSPITAL;

 CREATE TABLE PATIENTS(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL
 );

 CREATE TABLE DOCTORS(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL
 );

 CREATE TABLE APPOINTMENTS(
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES PATIENTS(id),
    FOREIGN KEY (doctor_id) REFERENCES DOCTORS(id)
 );

 INSERT INTO DOCTORS(name, specialization) VALUES
 ("Chinmayee Hankare", "Dietician"),
 ("Rushikesh Kshirsagar", "Surgeon");
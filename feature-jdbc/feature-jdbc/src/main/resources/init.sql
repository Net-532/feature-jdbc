CREATE DATABASE IF NOT EXISTS household_db;
USE household_db;

CREATE TABLE IF NOT EXISTS households (
id INT PRIMARY KEY,
address VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS members (
id INT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
age INT NOT NULL,
household_id INT,
FOREIGN KEY (household_id) REFERENCES households(id) ON DELETE CASCADE
);

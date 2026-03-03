🚀 HireSense – AI-Powered Smart Hiring Platform

HireSense is a full-stack web application designed to streamline the recruitment process using AI-driven resume analysis and intelligent job matching. The platform enables employers to manage job postings efficiently while helping candidates apply for relevant roles with automated resume scoring.

✨ Features
👤 Employer Module

Post and manage job listings

Activate / Deactivate jobs

View applicants per job

Filter applicants by status (Applied / Shortlisted / Rejected)

AI-based resume matching score

Sort jobs based on applicant count

👨‍💼 Candidate Module

User registration & login

Resume upload

Apply to relevant jobs

Track application status

🧠 AI Resume Scoring

HireSense integrates AI-based resume parsing to evaluate candidate resumes and generate a matching score based on job requirements. This helps employers quickly identify top candidates.

🛠️ Tech Stack

Backend: Java (Servlets & JSP)

Frontend: HTML, CSS, Bootstrap

Database: MySQL

Architecture: MVC Pattern

Server: Apache Tomcat

📂 Project Structure
HireSense/
 ├── src/main/java/hiresense
 │    ├── controller
 │    ├── dao
 │    ├── pojo
 │    └── dbutils
 ├── src/main/webapp
 │    ├── css
 │    ├── includes
 │    └── *.jsp
 ├── pom.xml
 └── README.md
⚙️ Setup Instructions

Clone the repository

git clone <your-repo-url>

Import the project into Eclipse as a Maven project

Configure MySQL database

Create a database

Update DB credentials inside DBConnection.java

Run on Apache Tomcat server

🎯 Project Objective

The goal of HireSense is to simplify the hiring workflow by integrating automation and intelligent matching into traditional recruitment systems.

📌 Future Enhancements

Advanced AI keyword extraction

Email notifications

Dashboard analytics

Role-based access improvements

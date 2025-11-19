Employee Project Management System

A Spring Boot 3 + JPA + MySQL based application demonstrating entity relationships, cascading, DTO mapping, Specifications, pagination, sorting, and LAZY vs EAGER loading behavior.

 Features Implemented
✔ Entity Relationships
Company → Projects

@OneToMany(mappedBy="company", cascade = ALL, orphanRemoval = true)

Deleting a company deletes all its projects (and related employees + addresses).

Project → Employees

@ManyToMany

A project can have many employees, and an employee can work on many projects.

Deleting a project does NOT delete employees.

Employee → Address

@OneToOne(cascade = ALL, orphanRemoval = true)

Saving employee saves address

Updating employee updates address

Deleting employee deletes address

Fetch Type Comparison

You can switch between LAZY and EAGER loading to compare SQL query count.

SQL logs enabled in application.properties.

REST API Endpoints
Company APIs
Method	Endpoint	Description
POST	/api/companies	Create a company
GET	/api/companies/{name}	Fetch company + projects + employees
DELETE	/api/companies/{id}	Delete company with cascade
Employee APIs
Method	Endpoint	Description
POST	/api/employees	Create employee (with address)
GET	/api/employees/{id}	Get employee details
PUT	/api/employees/{id}	Update employee + address
DELETE	/api/employees/{id}	Delete employee
GET	/api/employees	Pagination + sorting
GET	/api/employees/all	Fetch all
GET	/api/employees/{id}/projects	Get all projects of employee
Project APIs
Method	Endpoint	Description
POST	/api/projects	Create project
GET	/api/projects	Fetch all projects
GET	/api/projects/{projectId}/employees	Get employees of project
POST	/api/projects/{projectId}/assign/{employeeId}	Assign employee to project
DELETE	/api/projects/{projectId}	Delete project
Pagination & Sorting Example
GET /api/employees?page=0&size=5&sort=id,asc

Java Specifications Used For

Fetching projects by employee ID

Fetching employees by project ID

Fetching company by name (JOIN projects & employees)

Database Setup (XAMPP / MySQL)

Start Apache & MySQL from XAMPP

Open phpMyAdmin

Create database:

employeeprojectdb


Run the preload SQL in phpMyAdmin:

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE employee_project;
TRUNCATE TABLE employees;
TRUNCATE TABLE addresses;
TRUNCATE TABLE projects;
TRUNCATE TABLE companies;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO companies (id, company_name)
VALUES (1, 'AVK Tech Solutions');

INSERT INTO projects (id, project_title, company_id)
VALUES (1, 'Employee Management System', 1),
(2, 'Payroll Automation', 1),
(3, 'Attendance Tracker', 1);

INSERT INTO addresses (id, street, city, state, postal_code)
VALUES (1, 'Youthclub Road', 'Bhimavaram', 'Andhra Pradesh', '534202'),
(2, 'Main Road', 'Hyderabad', 'Telangana', '500001'),
(3, 'Ring Road', 'Vijayawada', 'Andhra Pradesh', '520001'),
(4, 'Tech Park', 'Bangalore', 'Karnataka', '560001');

INSERT INTO employees (id, full_name, address_id)
VALUES (1, 'John', 1),
(2, 'Wick', 2),
(3, 'Kiran', 3),
(4, 'Alice', 4);

INSERT INTO employee_project (employee_id, project_id)
VALUES (1, 1),
(2, 1),
(3, 2),
(4, 3),
(1, 3);

⚙️ Application Configuration

src/main/resources/application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/employeeprojectdb
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true

logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

How to Run
1️⃣ Start MySQL via XAMPP
2️⃣ Open project in IntelliJ
3️⃣ Run the Main class:
EmployeeprojectmanagementApplication

4️⃣ Use Postman for testing API endpoints
Technologies Used

Spring Boot 3

Spring Data JPA

Hibernate

MySQL (XAMPP / Docker Alternative)

Lombok

Java 17

✔ Tasks Completed (Required by Lead)

All 4 entities created

Correct relationships & cascading

DTOs implemented

Validation added

Pagination & sorting

Searching using Specifications

Assign employee ↔ project

Fetch company with nested details

SQL preload script ready

Clean folder structure

Proper naming conventions

Endpoints tested
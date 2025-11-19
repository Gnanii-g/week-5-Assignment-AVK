-- ============================
-- CLEAN START (optional)
-- ============================
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE employee_project;
TRUNCATE TABLE employees;
TRUNCATE TABLE addresses;
TRUNCATE TABLE projects;
TRUNCATE TABLE companies;

SET FOREIGN_KEY_CHECKS = 1;

-- ============================
-- INSERT BASE COMPANY
-- ============================
INSERT INTO companies (id, company_name)
VALUES (1, 'AVK Tech Solutions');

-- ============================
-- INSERT PROJECTS
-- ============================
INSERT INTO projects (id, project_title, company_id)
VALUES (1, 'Employee Management System', 1),
       (2, 'Payroll Automation', 1),
       (3, 'Attendance Tracker', 1);

-- ============================
-- INSERT ADDRESSES
-- ============================
INSERT INTO addresses (id, street, city, state, postal_code)
VALUES (1, 'Youthclub Road', 'Bhimavaram', 'Andhra Pradesh', '534202'),
       (2, 'Main Road', 'Hyderabad', 'Telangana', '500001'),
       (3, 'Ring Road', 'Vijayawada', 'Andhra Pradesh', '520001'),
       (4, 'Tech Park', 'Bangalore', 'Karnataka', '560001');

-- ============================
-- INSERT EMPLOYEES
-- ============================
INSERT INTO employees (id, full_name, address_id)
VALUES (1, 'john', 1),
       (2, 'wick', 2),
       (3, 'Kiran', 3),
       (4, 'Alice', 4);

-- ============================
-- ASSIGN EMPLOYEES TO PROJECTS
-- ============================
INSERT INTO employee_project (employee_id, project_id)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 3),
       (1, 3);   -- Surya works on Project 1 & 3

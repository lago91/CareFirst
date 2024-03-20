DROP TABLE IF EXISTS countries;

CREATE TABLE countries (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);


DROP TABLE IF EXISTS employees;
CREATE TABLE employees (
  employee_id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email_address VARCHAR(250) NOT NULL,
  phone VARCHAR(250) NOT NULL,
  birthdate DATE NOT NULL,
  job_title VARCHAR(250) NOT NULL,
  department VARCHAR(250) NOT NULL,
  location VARCHAR(250) NOT NULL,
  start_date DATE NOT NULL
);

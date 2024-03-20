package com.carefirst.code.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="employees")
public class Employee implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="employee_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long employeeId;
	
	@NotBlank(message = "Name is mandatory")
	@Column(name="first_name")
	private String firstName;
	
	@NotBlank(message = "Last Name is mandatory")
	@Column(name="last_name")
	private String lastName;
	
	@NotBlank(message = "Email address is mandatory")
	@Column(name="email_address")
	private String emailAddress;
	
	@NotBlank(message = "Phone is mandatory")
	@Column(name="phone")
	private String phone;
	
	//@NotBlank(message = "Birthdate is mandatory")
	@Column(name="birthdate")
	private Date birthdate;
	
	@NotBlank(message = "Job title is mandatory")
	@Column(name="job_title")
	private String jobTitle;
	
	@NotBlank(message = "Department is mandatory")
	@Column(name="department")
	private String department;
	
	@NotBlank(message = "Location is mandatory")
	@Column(name="location")
	private String location;

	//@NotBlank(message = "Stardate is mandatory")
	@Column(name="start_date")
	private Date startDate;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + ", phone=" + phone + ", birthdate=" + birthdate + ", jobTitle="
				+ jobTitle + ", department=" + department + ", location=" + location + ", startDate=" + startDate + "]";
	}
	
	
}

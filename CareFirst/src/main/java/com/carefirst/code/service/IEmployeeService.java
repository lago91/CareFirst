package com.carefirst.code.service;

import java.util.List;
import java.util.Optional;

import com.carefirst.code.entity.Employee;

public interface IEmployeeService {
	
	List<Employee> getEmployee();
	Optional<Employee> getEmployee(Long id);
	void deleteEmployee(Long id);
	Employee createEmployee(Employee employee);
	Employee updateEmployee(Long id, Employee employee);
}

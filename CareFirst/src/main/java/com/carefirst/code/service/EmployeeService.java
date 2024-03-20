package com.carefirst.code.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carefirst.code.dao.IEmployeeDao;
import com.carefirst.code.entity.Employee;

@Service
public class EmployeeService implements IEmployeeService{
	
	@Autowired
	private IEmployeeDao employeeDao;

	@Override
	public List<Employee> getEmployee() {
		// TODO Auto-generated method stub
		return (List<Employee>) employeeDao.findAll();
	}

	@Override
	public Optional<Employee> getEmployee(Long id) {
		// TODO Auto-generated method stub
		return employeeDao.findById(id);
	}

	@Override
	public void deleteEmployee(Long id) {
		// TODO Auto-generated method stub
		employeeDao.deleteById(id);
	}

	@Override
	public Employee createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDao.save(employee);
	}
	
	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		Employee updateEmployee = employeeDao.findById(id).orElseThrow();
	    updateEmployee.setFirstName(employee.getFirstName());
	    updateEmployee.setLastName(employee.getLastName());
	    updateEmployee.setEmailAddress(employee.getEmailAddress());
		return employeeDao.save(updateEmployee);
	}

}

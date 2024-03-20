package com.carefirst.code.dao;

import org.springframework.data.repository.CrudRepository;

import com.carefirst.code.entity.Employee;

public interface IEmployeeDao extends CrudRepository<Employee, Long>{

}

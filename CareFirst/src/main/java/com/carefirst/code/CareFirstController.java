package com.carefirst.code;

import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.carefirst.code.entity.Employee;
import com.carefirst.code.service.IEmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "employees")
public class CareFirstController {
	
	@Autowired
	private IEmployeeService employeeService;
	
	@GetMapping
	public List<Employee> getEmployees() {
		return employeeService.getEmployee();
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Employee> getEmployees(@PathVariable Long id) {
		Optional<Employee> emp = employeeService.getEmployee(id);
		if (emp.isPresent()) {
			return new ResponseEntity<>(emp.get(), HttpStatus.OK);
		}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
		Optional<Employee> emp = employeeService.getEmployee(id);
		if (emp.isPresent()) {
			employeeService.deleteEmployee(id);
			return new ResponseEntity<>(emp.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
		Employee emp = employeeService.createEmployee(employee);
	    return new ResponseEntity<>(emp, HttpStatus.CREATED);
	    
	}
	
	@PutMapping(value = "/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@Valid @RequestBody Employee employeeDetails) {
		Optional<Employee> emp = employeeService.getEmployee(id);
		if (emp.isPresent()) {
			employeeService.updateEmployee(id, employeeDetails);
			return new ResponseEntity<>(employeeDetails, HttpStatus.OK);
		}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
}

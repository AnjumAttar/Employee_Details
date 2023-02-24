package com.employee.services;

import java.util.List;

import com.employee.document.Employee;
import com.employee.exceptions.EmployeeException;
import com.employee.response.EmployeeResponse;

public interface EmployeeService {
	
	public Employee createEmployeeDetails(Employee emp);
	
	public Employee getEmployeeDetails(int id) throws EmployeeException;
	public List<Employee> getAllEmployeeDetails();
	public void deleteEmployee(int id);
	public Employee updateEmployee(int id,Employee updatedEmployee);

}

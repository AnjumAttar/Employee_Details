package com.employee.services;

import java.util.List;

import com.employee.document.Employee;
import com.employee.exceptions.EmployeeException;
import com.employee.response.EmployeeResponse;

public interface EmployeeService {
	
	public EmployeeResponse createEmployeeDetails(Employee emp);
	
	public EmployeeResponse getEmployeeDetailsById(int id) throws EmployeeException;
	public List<EmployeeResponse> getAllEmployeeDetails();
	public void deleteEmployee(int id);
	public EmployeeResponse updateEmployee(int id,Employee updatedEmployee);

	

}

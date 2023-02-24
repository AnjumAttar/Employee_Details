package com.employee.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.document.Employee;
import com.employee.exceptions.EmployeeException;
import com.employee.repository.EmployeeRepository;
import com.employee.response.EmployeeResponse;
import com.employee.services.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo;
	
	
	@Override
	public Employee createEmployeeDetails(Employee emp) {
		
		Employee employee=null;
		employee = empRepo.save(emp);
		if(employee==null) {
			throw new EmployeeException("No employee data is available"); 
		}
		return employee;
	}

	@Override
	public Employee getEmployeeDetails (int id) throws EmployeeException  {
		
		Employee empDetails=null;
	Optional<Employee> optional =	empRepo.findById(id);
	
	if(optional.isPresent()) {
		empDetails = optional.get();
	}
	else {
		throw new EmployeeException("Employee does not exist");
	}
	 
		return empDetails;
	}

	@Override
	public List<Employee> getAllEmployeeDetails() {
		
		EmployeeResponse empRes=new EmployeeResponse();
		List<Employee> allEmplyees=empRepo.findAll();
		
		return allEmplyees;
	}

	@Override
	public void deleteEmployee(int id) {
		empRepo.deleteById(null);
		
	}

	@Override
	public Employee updateEmployee(int id,Employee updatedEmployee) {
		Optional<Employee> optional=empRepo.findById(id);
		Employee employee=null;
		Employee newEmployee=null;
		if(optional.isPresent()) {
			employee = optional.get();
			employee.setCtc(updatedEmployee.getCtc());
			newEmployee = empRepo.save(employee);
			}
		 
		else {
			
			throw new EmployeeException("Employee does not exist");
		}
		
		
		return newEmployee;
		
		
		
	}
	
	

}

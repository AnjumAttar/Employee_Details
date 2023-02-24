package com.employee.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.document.Employee;
import com.employee.exceptions.EmployeeException;
import com.employee.response.EmployeeResponse;
import com.employee.services.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	public EmployeeService empService;
	
	@GetMapping("/allEmployeesDetails")
	public ResponseEntity<List<Employee>>  getAllEmployeesDetails() {
		
		List<Employee> listOfEmployees = new ArrayList<>();
		listOfEmployees = empService.getAllEmployeeDetails();
		return new ResponseEntity<>(listOfEmployees, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/employeeDetails/{id}")
	public ResponseEntity<Employee> getEmployeeDetails(@PathVariable int id) {
		Employee Employee = null;
		Employee = empService.getEmployeeDetails(id);
		return new ResponseEntity<>(Employee, HttpStatus.OK);
	
	
	}
	
	
	@PostMapping(value = "/newemployee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee entity ) {
		
		Employee emp=empService.createEmployeeDetails(entity);
		
		return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
	}
	
	
	@PutMapping(value="/update/{id}")
	public ResponseEntity<Employee> updateEmployeeDetails(@PathVariable int id,@RequestBody Employee employee) {
		
		Employee emp=empService.updateEmployee(id, employee);
		
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	
	
		
	}
	
	@DeleteMapping(value="/delete/{id}")
	public void deleteEmployeeDetails(@PathVariable int id) {
		empService.deleteEmployee(id);
		
	}
	
	
	

	
	public List<EmployeeResponse> createResponse(List<Employee> empList){
		
		List<EmployeeResponse> responseList = new ArrayList<>();
		
		try {
			
			empList.forEach(emp ->{
			EmployeeResponse empResponse = new EmployeeResponse();
			BeanUtils.copyProperties(emp, empResponse);
			responseList.add(empResponse);
				
			});
			
			
		 }catch(Exception e) {
			 System.out.println(e);
		 }
		
		return responseList;
		
	}

}

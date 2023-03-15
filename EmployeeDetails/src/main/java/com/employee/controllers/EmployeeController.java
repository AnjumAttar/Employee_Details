package com.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.document.Employee;
import com.employee.response.EmployeeResponse;
import com.employee.serviceimpl.KafkaProducer;
import com.employee.services.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	public EmployeeService empService;

	@Autowired
	public KafkaProducer kafkaProduce;
	EmployeeResponse empRespons;
	List<EmployeeResponse> empResponseList;
	
	

	@GetMapping("/allEmployeesDetails")
	public ResponseEntity<List<EmployeeResponse>> getAllEmployeesDetails() {

		empResponseList = empService.getAllEmployeeDetails();
        return new ResponseEntity<>(empResponseList, HttpStatus.OK);
	}
	

	@GetMapping("/employeeDetails/{id}")
	public ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable int id) {
		empRespons = empService.getEmployeeDetailsById(id);
		return new ResponseEntity<>(empRespons, HttpStatus.OK);

	}

	@PostMapping(value = "/newemployee")
	public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody Employee entity) {
         empRespons = empService.createEmployeeDetails(entity);
         return new ResponseEntity<EmployeeResponse>(empRespons, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<EmployeeResponse> updateEmployeeDetails(@PathVariable int id,
			                                                      @RequestBody Employee employee) {

		empRespons = empService.updateEmployee(id, employee);
		return new ResponseEntity<EmployeeResponse>(empRespons, HttpStatus.OK);
    }

	@DeleteMapping(value = "/delete/{id}")
	public void deleteEmployeeDetails(@PathVariable int id) {
		empService.deleteEmployee(id);

	}

	

}

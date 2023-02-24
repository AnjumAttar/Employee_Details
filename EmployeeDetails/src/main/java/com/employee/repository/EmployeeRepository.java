package com.employee.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.employee.document.Employee;
import com.employee.response.EmployeeResponse;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Integer>{
	
	
	  
	
	

}

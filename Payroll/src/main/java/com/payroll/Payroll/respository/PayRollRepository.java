package com.payroll.Payroll.respository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import com.payroll.Payroll.model.Payroll;


@Repository
public interface PayRollRepository extends MongoRepository<Payroll, Integer>{
	
	@Query("{'employee_id' : ?0}")
	Payroll  findEmployeeById(int empId);

}

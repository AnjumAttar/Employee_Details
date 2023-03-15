package com.payroll.Payroll.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.payroll.Payroll.response.PayRollResponse;
import com.payroll.Payroll.service.PayrollService;
import com.payroll.Payroll.serviceImpl.KafkaConsumerForEmployee;

@RestController
public class PayrollInfoController {
	
	@Autowired
	PayrollService payrollService;
	
	@Autowired
	KafkaConsumerForEmployee kafkaConsumerForEmployee;
	PayRollResponse payRollResponse=null;
	
	@GetMapping("/payroll/{id}")
	public ResponseEntity<PayRollResponse> getPayRollInformationById(@PathVariable int id) {
		payRollResponse   =	payrollService.getEmployeePayrollById(id);
	return new ResponseEntity<PayRollResponse>(payRollResponse, HttpStatus.OK);
		
	}
	
	

}

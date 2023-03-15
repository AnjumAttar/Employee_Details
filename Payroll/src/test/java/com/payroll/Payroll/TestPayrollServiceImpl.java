package com.payroll.Payroll;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.payroll.Payroll.model.Payroll;
import com.payroll.Payroll.response.PayRollResponse;
import com.payroll.Payroll.respository.PayRollRepository;
import com.payroll.Payroll.serviceImpl.PayrollServiceImpl;



@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TestPayrollServiceImpl {
	
	
	
	PayrollServiceImpl payrollServiceImpl;
	
	Payroll payroll;
	
	
	@Mock
	PayRollRepository payRollRepository;
	
	@Autowired
	ModelMapper  modelMapper;

	
	
	@BeforeEach
	public void setup() {
		this.payrollServiceImpl = new PayrollServiceImpl(payRollRepository, modelMapper);
		payroll=new Payroll();
		payroll.setPayRollid(101);
		payroll.setEmployee_id(201);
		payroll.setBankAccountNumber(12345);
		payroll.setMonthlySalary(10000.00);
		payroll.setRegisteredBank("HDFC");

		
	}
	
	
	@Test
	void testgetEmployeeDetailsById() {
		Optional<Payroll> optional= Optional.ofNullable(payroll); 
		when(payRollRepository.findById(101)).thenReturn(optional);
	PayRollResponse resPayRoll =	payrollServiceImpl.getEmployeePayrollById(101);
	
	System.out.println("get response id:"+resPayRoll.getPayRollid());
	assertEquals(payroll.toString(),resPayRoll.toString());
	
	
		

	}

}

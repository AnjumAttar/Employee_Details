package com.payroll.Payroll.serviceImpl;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.employee.response.EmployeeResponse;
import com.payroll.Payroll.model.Payroll;
import com.payroll.Payroll.respository.PayRollRepository;

@Service
public class KafkaConsumerForEmployee {

	@Autowired
	PayRollRepository payRollRepository;
	private EmployeeResponse newEmployee;
	private CountDownLatch latch = new CountDownLatch(1);

	public EmployeeResponse getNewEmployee() {
		return newEmployee;
	}

	public void setNewEmployee(EmployeeResponse newEmployee) {
		this.newEmployee = newEmployee;
	}

	public CountDownLatch getLatch() {
		return latch;
	}

	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(EmployeeResponse employee) {
		System.out.println("Received paylod" + employee);
		setNewEmployee(employee);
		latch.countDown();
		getUpdatedEmployeeDeatils();
		
		
		

	}

	public void getUpdatedEmployeeDeatils() {
		
		if(getNewEmployee() !=null) {
		System.out.println("Updated EMployee:" + getNewEmployee().toString());
		Payroll payRoll = payRollRepository.findEmployeeById(getNewEmployee().getId());
		System.out.println("Pay roll whose salary to be updated :" + payRoll.toString());
		double setupDatedSalary = newEmployee.getCtc() / 12;
		payRoll.setMonthlySalary(setupDatedSalary);
		Payroll updatedPayroll = payRollRepository.save(payRoll);
		System.out.println("Updated PayRoll:" + updatedPayroll.toString());

		}
		
	}

}

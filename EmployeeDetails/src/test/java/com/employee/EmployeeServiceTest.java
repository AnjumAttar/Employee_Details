package com.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.employee.document.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.response.EmployeeResponse;
import com.employee.serviceimpl.EmployeeServiceImpl;
import com.employee.serviceimpl.KafkaProducer;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

class EmployeeServiceTest {

	
	EmployeeServiceImpl employeeServiceImpl;

	Employee emp ;
	EmployeeResponse empResponse = new EmployeeResponse();
	
	@Mock
	KafkaProducer kafkaProducer;
	
	@Mock
	EmployeeRepository employeeRepository;



	@Autowired
	ModelMapper maodelMapper;
	

	
	List<EmployeeResponse> empResonseList = null;

	@BeforeEach
	void setup() {
		emp=new Employee();
		emp.setId(201);
		emp.setAge(25);
		emp.setCtc(150000.00);
		emp.setFirst_name("priyanka");
		emp.setLast_name("chopra");
		emp.setOrganisation("Acc");
		this.employeeServiceImpl = new EmployeeServiceImpl(employeeRepository, kafkaProducer, maodelMapper, empResponse, empResonseList);
		
	}

	@Test
	void testcreateEmployeeDetails() {
		when(employeeRepository.save(emp)).thenReturn(emp);
		empResponse = employeeServiceImpl.createEmployeeDetails(emp);
		assertEquals(empResponse.getId(), emp.getId());
		
		

	}

	@Test
	void testgetEmployeeDetailsById() {
		Optional<Employee> optional= Optional.ofNullable(emp);
		when(employeeRepository.findById(201)).thenReturn(optional);
		empResponse = employeeServiceImpl.getEmployeeDetailsById(201);
		assertEquals(201, empResponse.getId());
		

	}
	
	@Test
	void testupdateEmployee() {
		emp.setCtc(500000.00);
		emp.setFirst_name("Madhuri");
		emp.setLast_name("dixit");
		emp.setOrganisation("Acc");
		when(employeeRepository.save(emp)).thenReturn(emp);
		empResponse = employeeServiceImpl.updateEmployee(201, emp);
		assertEquals(emp.getCtc(),empResponse.getCtc());
		
		
	}
	
	
	@Test
	void testdeleteEmployee() {
		
	employeeServiceImpl.deleteEmployee(201);
	verify(employeeRepository).deleteById(201);
	
		
	}

}

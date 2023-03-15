package com.employee.serviceimpl;

import java.util.List;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	KafkaProducer kafkaProducer;

	@Autowired
   	ModelMapper maodelMapper;
	private EmployeeResponse empResponse = null;
	
	private List<EmployeeResponse> empResonseList = null;

	public EmployeeResponse getEmpResponse() {
		return empResponse;
	}

	public void setEmpResponse(EmployeeResponse empResponse) {
		this.empResponse = empResponse;
	}

	public List<EmployeeResponse> getEmpResonseList() {
		return empResonseList;
	}

	public void setEmpResonseList(List<EmployeeResponse> empResonseList) {
		this.empResonseList = empResonseList;
	}

	public EmployeeServiceImpl() {

	}

	public EmployeeServiceImpl(EmployeeRepository empRepo, KafkaProducer kafkaProducer, ModelMapper maodelMapper,
			EmployeeResponse empResponse, List<EmployeeResponse> empResonseList) {
		super();
		this.empRepo = empRepo;
		this.kafkaProducer = kafkaProducer;
		this.maodelMapper = maodelMapper;
		this.empResponse = empResponse;
		this.empResonseList = empResonseList;
	}

	@Override
	public List<EmployeeResponse> getAllEmployeeDetails() {

		List<Employee> allEmplyees = empRepo.findAll();
		empResonseList = maodelMapper.map(allEmplyees, new TypeToken<List<EmployeeResponse>>() {
		}.getType());
		return empResonseList;
	}

	@Override
	public EmployeeResponse getEmployeeDetailsById(int id) throws EmployeeException {
		Employee empDetails = null;
		Optional<Employee> optional = empRepo.findById(id);

		if (optional.isPresent()) {
			empDetails = optional.get();
			return maodelMapper.map(empDetails, EmployeeResponse.class);
		} else {
			throw new EmployeeException("Employee does not exist");
		}

		
	}

	@Override
	public EmployeeResponse createEmployeeDetails(Employee emp) {
		Employee employee = null;
		employee = empRepo.save(emp);
		if (employee != null) {
			return maodelMapper.map(employee, EmployeeResponse.class);
		}else {
		
			throw new EmployeeException("No employee data is available");
		}
		 
	}

	@Override
	public EmployeeResponse updateEmployee(int id, Employee updatedEmployee) {

		Employee employee = null;
		employee = empRepo.save(updatedEmployee);
		empResponse = maodelMapper.map(employee, EmployeeResponse.class);
		if (employee != null) {
			kafkaProducer.sendMessage(empResponse);

		} else {
			throw new EmployeeException("No employee data is available");
		}
		return empResponse;

	}

	@Override
	public void deleteEmployee(int id) {
		empRepo.deleteById(id);

	}

}

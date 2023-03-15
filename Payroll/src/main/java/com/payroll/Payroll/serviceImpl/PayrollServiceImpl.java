package com.payroll.Payroll.serviceImpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.Payroll.model.Payroll;
import com.payroll.Payroll.response.PayRollResponse;
import com.payroll.Payroll.respository.PayRollRepository;
import com.payroll.Payroll.service.PayrollService;

@Service
public class PayrollServiceImpl implements PayrollService {

public 	Payroll payroll = null;
	

	@Autowired
	PayRollRepository payRollRepository;

	@Autowired
    ModelMapper modelMapper;
	
	@Autowired
	KafkaConsumerForEmployee kafkaConsumerForEmployee;
	
	
	public PayrollServiceImpl() {
		
	}

	public PayrollServiceImpl(PayRollRepository payRollRepository, ModelMapper modelMapper) {
		super();
		this.payRollRepository = payRollRepository;
		this.modelMapper = modelMapper;
		
	}




	@Override
	public PayRollResponse getEmployeePayrollById(int id) {
		Optional<Payroll> optional = payRollRepository.findById(id);
		if (optional.isPresent()) {
			payroll = optional.get();
			return modelMapper.map(payroll, PayRollResponse.class);

		}

		else {
			
			throw new RuntimeException("No record exist in db");
		}

		

	}

}

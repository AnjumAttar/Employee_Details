package com.employee.serviceimpl;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.employee.response.EmployeeResponse;

@Service
public class KafkaProducer {
	
	
	@Autowired
	NewTopic topic;
	
	@Autowired
	KafkaTemplate<String,EmployeeResponse> kafkaTemplate;
	
	
	Message<EmployeeResponse> message;
	
	
	
	

	public Message<EmployeeResponse> getMessage() {
		return message;
	}


	public void setMessage(Message<EmployeeResponse> message) {
		this.message = message;
	}


	public KafkaProducer(NewTopic topic, KafkaTemplate<String, EmployeeResponse> kafkaTemplate) {
		super();
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	
	public void sendMessage(EmployeeResponse employee) {
	
		message =MessageBuilder.withPayload(employee)
				                  .setHeader(KafkaHeaders.TOPIC,"testTopic")
				                  .build();
		
		setMessage(message);
		kafkaTemplate.send(getMessage());
		System.out.println("Send message from producer:"+message.toString());
	}
	
	



}

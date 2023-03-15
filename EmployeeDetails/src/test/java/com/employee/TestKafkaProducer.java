package com.employee;

import static org.mockito.Mockito.atLeastOnce;

import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.messaging.Message;

import com.employee.response.EmployeeResponse;
import com.employee.serviceimpl.KafkaProducer;


@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9093", "port=9093" })
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TestKafkaProducer {


    
    private KafkaProducer producer;
    
    
    @Mock
    KafkaTemplate<String, EmployeeResponse> kafkaTemplate;
    
    @Mock
   
	NewTopic topic;


   @BeforeEach
    public void setup() {
	   
    	this.producer=new KafkaProducer(this.topic,this.kafkaTemplate);
    	
    }
    


    @Test
    public void testKafkaProducer() 
      throws Exception {
    	EmployeeResponse employee=new EmployeeResponse();
    	employee.setId(122);
    	employee.setFirst_name("madhhuri");
    	employee.setLast_name("Dixit");
    	employee.setCtc(150000.0);
    	employee.setAge(25);
    	employee.setOrganisation("accenture");
    	producer.sendMessage(employee);
    Message<EmployeeResponse> msg=	producer.getMessage();
    Mockito.verify(kafkaTemplate,atLeastOnce()).send(msg);
        

    }
}

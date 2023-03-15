package com.payroll.Payroll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import com.employee.response.EmployeeResponse;
import com.payroll.Payroll.serviceImpl.KafkaConsumerForEmployee;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })

class TestKafkaConsumer {
	
	 @Autowired
	    private EmbeddedKafkaBroker embeddedKafkaBroker;
	 
	 @Autowired
	 KafkaConsumerForEmployee kafkaConsumerForEmployee;
	 EmployeeResponse empResponse;
	 
	 
	@BeforeEach
	public void setup() {
		
		empResponse=new EmployeeResponse();
		empResponse.setId(122);
		empResponse.setFirst_name("SOny");
		empResponse.setLast_name("Verma");
		empResponse.setAge(25);
		empResponse.setCtc(100000);
		empResponse.setOrganisation("Acc");
		Map<String, Object> configs = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
		Producer<String, EmployeeResponse> producer = new DefaultKafkaProducerFactory<>(configs, new StringSerializer(), new JsonSerializer<EmployeeResponse>()).createProducer();
		producer.send(new ProducerRecord<>("testTopic", "payrollGroup", empResponse));
		producer.flush();
		
	}
	
	
	@Test
	public void testConsumer() throws Exception {
		
		 boolean messageConsumed = kafkaConsumerForEmployee.getLatch().await(10,TimeUnit.SECONDS);
	       assertTrue(messageConsumed);
	       if(kafkaConsumerForEmployee.getNewEmployee()!=null) {
	       assertEquals(empResponse.toString(),kafkaConsumerForEmployee.getNewEmployee().toString());
	       }
		
	}

}

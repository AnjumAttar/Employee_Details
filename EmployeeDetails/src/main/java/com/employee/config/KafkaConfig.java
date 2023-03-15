package com.employee.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
	
	@Value("${spring.kafka.topic.name}")
	private String topicName;


    
    
    @Bean
    NewTopic topic() {
        return TopicBuilder.name("topicName")
                .build();
    }
    
    @Bean
    public ModelMapper modelMapper() {
		return new ModelMapper();
    	
    }
    
   
}

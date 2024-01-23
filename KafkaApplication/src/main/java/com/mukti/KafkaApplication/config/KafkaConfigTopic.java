package com.mukti.KafkaApplication.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfigTopic {
	 @Bean
	    public NewTopic kafkaTopic(){
	        return TopicBuilder.name("ecom-order").build();
	    }
}

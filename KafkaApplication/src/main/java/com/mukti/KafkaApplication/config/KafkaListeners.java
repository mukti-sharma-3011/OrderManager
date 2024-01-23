package com.mukti.KafkaApplication.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mukti.KafkaApplication.entity.Order;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaListeners {
	@KafkaListener(topics = "ecom-order", groupId = "grouId")
    void listener(String data) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Order effectiveJava = mapper.readValue(data, Order.class);
        System.out.println("Mukti" + effectiveJava.toString());
    }
}

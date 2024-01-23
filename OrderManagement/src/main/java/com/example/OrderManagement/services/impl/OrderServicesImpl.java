package com.example.OrderManagement.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.OrderManagement.entity.Order;
import com.example.OrderManagement.repo.OrderRepository;
import com.example.OrderManagement.services.OrderServices;
import com.example.OrderManagement.topics.KafkaTopic;
import com.example.OrderManagement.util.OrderStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class OrderServicesImpl implements OrderServices {
    private Logger logger = LoggerFactory.getLogger(OrderServices.class);

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
 
    @Autowired
    private OrderRepository repo;
    @Override
    public void createOrder(Order order) {
//        UUID uuid = UUID.randomUUID();
        Order order1=new Order();
//        order1.setId(String.valueOf(uuid));
        order1.setStatus(OrderStatus.PLACED);
        order1.setOrderDate(new Date());
        order1.setAmount(order.getAmount());
        order1.setQuantity(order.getQuantity());
        ObjectMapper Obj = new ObjectMapper();
        logger.info(order1.toString());
        repo.save(order1);
        try {
            // Converting the Java object into a JSON string
            String jsonStr = Obj.writeValueAsString(order1);
            // Displaying Java object into a JSON string
            System.out.println(jsonStr);

            kafkaTemplate.send(KafkaTopic.ECOM_ORDER, jsonStr);
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
	@Override
	public String getStatus(Integer orderId) {
		Order order= repo.findById(orderId).orElse(null);
		if(order==null) {
			logger.info("no object with this id exist");
			return "Not Found";
		}
		return order.getStatus().toString();
	}
	@Override
	public Order updateShippedStatus(Integer orderId) {
		Order order=repo.findById(orderId).orElse(null);
		if(order==null) {
			logger.info("no object with this id exist");
			return null;
		}
		order.setStatus(OrderStatus.SHIPPED);
		repo.save(order);
		ObjectMapper Obj = new ObjectMapper();
        try {
            // Converting the Java object into a JSON string
            String jsonStr = Obj.writeValueAsString(order);
            // Displaying Java object into a JSON string
            System.out.println(jsonStr);
            kafkaTemplate.send(KafkaTopic.ECOM_ORDER, jsonStr);   
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		return order;
	}
}

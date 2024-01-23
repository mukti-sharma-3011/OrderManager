package com.example.OrderManagement.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OrderManagement.entity.Order;
import com.example.OrderManagement.services.OrderServices;



@RestController
@RequestMapping(value = "/ecom")
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    @GetMapping("hi")
    public String get(){
        return "Hi";
    }

    @PostMapping(value = "/create-order")
    public ResponseEntity<Objects>createOrder(@RequestBody Order order){
        orderServices.createOrder(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value="/status/{orderId}")
    public ResponseEntity<String> getStatus(@PathVariable Integer orderId){
    	String status=orderServices.getStatus(orderId);
    	return ResponseEntity.ok(status);
    }
    @PatchMapping("/update/{orderId}")
    public ResponseEntity<Order> shippedStatus(@PathVariable Integer orderId) {
    	Order order=orderServices.updateShippedStatus(orderId);
    	return ResponseEntity.ok(order);
    }
}


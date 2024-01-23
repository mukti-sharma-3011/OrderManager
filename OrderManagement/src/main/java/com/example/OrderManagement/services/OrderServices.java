package com.example.OrderManagement.services;

import com.example.OrderManagement.entity.Order;

public interface OrderServices {
    public void createOrder(Order order);
    public String getStatus(Integer orderId);
    public Order updateShippedStatus(Integer orderId);
    
}

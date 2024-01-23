package com.example.OrderManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OrderManagement.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}

package com.example.OrderManagement.entity;

import java.util.Date;

import com.example.OrderManagement.util.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="orders_info")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private double amount;
    private Date orderDate;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Integer id, double amount, Date orderDate, int quantity, OrderStatus status) {
		super();
		this.id = id;
		this.amount = amount;
		this.orderDate = orderDate;
		this.quantity = quantity;
		this.status = status;
	}

	public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", orderDate=" + orderDate +
                ", quantity=" + quantity +
                '}';
    }
}

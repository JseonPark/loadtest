package com.example.loadtest.domain.order.repository;

import com.example.loadtest.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

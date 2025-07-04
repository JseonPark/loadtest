package com.example.loadtest.domain.order.service;

import com.example.loadtest.domain.order.dto.OrderRequestDTO;
import com.example.loadtest.domain.order.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    Long createOrder(OrderRequestDTO requestDto);
    List<OrderResponseDTO> getOrders(Long userId);
}

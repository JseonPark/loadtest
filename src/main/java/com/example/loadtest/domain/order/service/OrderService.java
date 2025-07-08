package com.example.loadtest.domain.order.service;

import com.example.loadtest.domain.order.dto.OrderItemDTO;
import com.example.loadtest.domain.order.dto.OrderRequestDTO;
import com.example.loadtest.domain.order.dto.OrderResponseDTO;
import com.example.loadtest.domain.order.dto.OrderSummaryDTO;

import java.util.List;

public interface OrderService {
    Long createOrder(OrderRequestDTO requestDto);
    OrderSummaryDTO createOrderItems(List<OrderItemDTO> itemDTOs);
    List<OrderResponseDTO> getOrders(Long userId);

}

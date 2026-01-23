package com.example.loadtest.domain.order.repository;

import com.example.loadtest.domain.order.dto.OrderHistoryResponseDTO;
import com.example.loadtest.domain.order.entity.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryCustom {
    Page<OrderHistoryResponseDTO> searchOrders(Long userId, String storeName, String menuName, Pageable pageable);
}

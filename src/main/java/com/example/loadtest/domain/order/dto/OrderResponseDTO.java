package com.example.loadtest.domain.order.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {
    private Long orderId;
    private String storeName;
    private LocalDateTime orderTime;
    private List<OrderItemDTO> items;
    private int totalPaid;
}

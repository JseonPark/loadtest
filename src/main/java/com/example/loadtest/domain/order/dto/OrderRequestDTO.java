package com.example.loadtest.domain.order.dto;

import com.example.loadtest.domain.delivery.dto.DeliveryInfoDTO;
import com.example.loadtest.domain.payment.dto.PaymentDTO;

import java.util.List;

public class OrderRequestDTO {
        private Long userId;
        private Long storeId;
        private String mainMenuName;
        private List<OrderItemDTO> orderItems;
        private PaymentDTO payment;
        private DeliveryInfoDTO delivery;

}

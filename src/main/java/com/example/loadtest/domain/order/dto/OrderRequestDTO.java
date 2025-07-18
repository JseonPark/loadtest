package com.example.loadtest.domain.order.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDTO {

  private Long userId;

  private Long storeId;

  private String mainMenuName;

  private List<OrderItemDTO> orderItems;

  private PaymentDTO payment;

  private DeliveryInfoDTO delivery;

}

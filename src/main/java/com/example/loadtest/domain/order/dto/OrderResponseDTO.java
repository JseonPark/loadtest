package com.example.loadtest.domain.order.dto;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderResponseDTO {

  private Long orderId;

  private String storeName;

  private ZonedDateTime orderTime;

  private List<OrderItemDTO> items;

  private int totalPaid;
}

package com.example.loadtest.domain.order.dto;

import com.example.loadtest.domain.order.entity.Order;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class OrderHistoryResponseDTO {

  private final String orderSummaryText;
  private final String storeName;
  private final String status;
  private final ZonedDateTime orderTime;
  private final int totalPrice;
  private final int discountAmount;
  private final int paidPrice;

    public OrderHistoryResponseDTO(Order order) {
        this.orderSummaryText = order.getOrderSummaryText();
        this.storeName = order.getStore().getName();
        this.status = order.getStatus();
        this.orderTime = order.getOrderTime();
        this.discountAmount = (order.getPayment() != null) ? order.getPayment().getDiscountAmount() : 0;
        this.totalPrice = (order.getPayment() != null) ? order.getPayment().getTotalPaid() : 0;
        this.paidPrice = this.totalPrice - this.discountAmount;
    }
}

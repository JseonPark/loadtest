package com.example.loadtest.domain.order.dto;

import lombok.Getter;

@Getter
public class PaymentDTO {
    private String paymentMethod;
    private int menuTotal;
    private int deliveryFee;
    private int discountAmount;
    private int totalPaid;
}
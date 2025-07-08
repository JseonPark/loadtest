package com.example.loadtest.domain.order.dto;

import lombok.Getter;

@Getter
public class OrderItemDTO {
    private Long menuId;
    private int quantity;
    private int price;

    private String menuName;
}
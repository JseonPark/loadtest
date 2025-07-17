package com.example.loadtest.domain.order.dto;

import lombok.Getter;

@Getter
public class DeliveryInfoDTO {

  private String phoneNumber;

  private String deliveryAddress;

  private String messageToRider;

  private String messageToStore;
}

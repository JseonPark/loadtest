package com.example.loadtest.domain.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "delivery_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  private String phoneNumber;

  private String deliveryAddress;

  private String messageToRider;

  private String messageToStore;

  @Column(updatable = false)
  private ZonedDateTime createdAt;

  @PrePersist
  public void prePersist() {
    this.createdAt = ZonedDateTime.now();
  }


  //== 연관관계 편의 메서드 ==//
  // package-private 접근제한자. Order 엔티티를 통해서만 호출되어야 한다.
  void assignToOrder(Order order) {
    this.order = order;
  }

  //== 생성 메서드 ==//
  public static DeliveryInfo create(String phoneNumber, String deliveryAddress,
      String messageToRider, String messageToStore) {
    DeliveryInfo deliveryInfo = new DeliveryInfo();
    deliveryInfo.phoneNumber = phoneNumber;
    deliveryInfo.deliveryAddress = deliveryAddress;
    deliveryInfo.messageToRider = messageToRider;
    deliveryInfo.messageToStore = messageToStore;
    return deliveryInfo;
  }
}

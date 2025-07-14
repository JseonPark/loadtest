package com.example.loadtest.domain.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    //== 연관관계 편의 메서드 ==//
    void assignToOrder(Order order) {
        this.order = order;
    }

    //== 생성 메서드 ==//
    public static DeliveryInfo create(String phoneNumber, String deliveryAddress, String messageToRider, String messageToStore) {
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.phoneNumber = phoneNumber;
        deliveryInfo.deliveryAddress = deliveryAddress;
        deliveryInfo.messageToRider = messageToRider;
        deliveryInfo.messageToStore = messageToStore;
        return deliveryInfo;
    }
}

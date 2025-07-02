package com.example.loadtest.domain.delivery;

import com.example.loadtest.domain.order.entity.Order;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private String phoneNumber;
    private String deliveryAddress;
    private String messageToRider;
    private String messageToStore;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public static DeliveryInfo create(Order order, String phoneNumber, String deliveryAddress, String messageToRider, String messageToStore) {
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.order = order;
        deliveryInfo.phoneNumber = phoneNumber;
        deliveryInfo.deliveryAddress = deliveryAddress;
        deliveryInfo.messageToRider = messageToRider;
        deliveryInfo.messageToStore = messageToStore;
        return deliveryInfo;
    }


}

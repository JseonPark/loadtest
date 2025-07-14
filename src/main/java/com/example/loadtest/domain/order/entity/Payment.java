package com.example.loadtest.domain.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private String paymentMethod;
    private int menuTotal;
    private int deliveryFee;
    private int discountAmount;
    private int totalPaid;

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
    public static Payment create(String paymentMethod, int menuTotal, int deliveryFee, int discountAmount) {
        Payment payment = new Payment();
        payment.paymentMethod = paymentMethod;
        payment.menuTotal = menuTotal;
        payment.deliveryFee = deliveryFee;
        payment.discountAmount = discountAmount;
        payment.totalPaid = (menuTotal + deliveryFee) - discountAmount;
        return payment;
    }
}

package com.example.loadtest.domain.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

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

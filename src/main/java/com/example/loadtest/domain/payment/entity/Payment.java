package com.example.loadtest.domain.payment.entity;

import com.example.loadtest.domain.order.entity.Order;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private String paymentMethod;
    private int menuTotal;
    private int deliveryFee;
    private int discountAmount;
    private int totalPaid;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public static Payment create(Order order, String paymentMethod, int menuTotal, int deliveryFee, int discountAmount) {
        Payment payment = new Payment();
        payment.order = order;
        payment.paymentMethod = paymentMethod;
        payment.menuTotal = menuTotal;
        payment.deliveryFee = deliveryFee;
        payment.discountAmount = discountAmount;
        payment.totalPaid = menuTotal + deliveryFee - discountAmount;
        return payment;
    }

}

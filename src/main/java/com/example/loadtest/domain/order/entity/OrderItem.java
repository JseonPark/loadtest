package com.example.loadtest.domain.order.entity;

import com.example.loadtest.domain.store.entity.Menu;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    private int quantity;

    private int price; // 주문 시점 가격 스냅샷

    private LocalDateTime createdAt;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    //== 생성 메서드 ==//
    public static OrderItem create(Menu menu, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.menu = menu;
        orderItem.quantity = quantity;
        orderItem.price = menu.getPrice(); // 주문 시점의 가격 스냅샷
        return orderItem;
    }

    //== 연관관계 편의 메서드 ==//
    void assignTo(Order order) {
        this.order = order;
    }
}

package com.example.loadtest.domain.order.entity;


import com.example.loadtest.domain.store.entity.Store;
import com.example.loadtest.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    private String status;
    private String menuSummary;
    private LocalDateTime orderTime;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.orderTime = LocalDateTime.now();
        this.status = "ORDERED";
    }

    public static Order create(User user, Store store, String menuSummary) {
        Order order = new Order();
        order.user = user;
        order.store = store;
        order.menuSummary = menuSummary;
        return order;
    }
}

    
    
    

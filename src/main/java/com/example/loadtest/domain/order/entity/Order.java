package com.example.loadtest.domain.order.entity;


import com.example.loadtest.domain.order.dto.OrderItemDTO;
import com.example.loadtest.domain.store.entity.Store;
import com.example.loadtest.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    private String status;
    private String orderSummaryText;
    private LocalDateTime orderTime;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = "ORDERED";
    }

    public static Order create(User user, Store store, List<OrderItemDTO> items, LocalDateTime orderTime) {

        if (user == null || store == null || items == null || orderTime == null) {
            throw new IllegalArgumentException("필수값 누락");
        }

        String mainMenuName = items.get(0).getMenuName();
        int others = items.size() - 1;


        String summary = (others > 0)
                ? String.format("%s 외 %d건", mainMenuName, others)
                : mainMenuName;


        Order order = new Order();
        order.user = user;
        order.store = store;
        order.orderSummaryText = summary;
        order.orderTime = orderTime;
        return order;
    }
}

    
    
    

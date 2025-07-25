package com.example.loadtest.domain.order.entity;

import com.example.loadtest.domain.store.entity.Store;
import com.example.loadtest.domain.user.entity.User;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems = new ArrayList<>();

  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Payment payment;

  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private DeliveryInfo deliveryInfo;

  private String status;

  private String orderSummaryText;

  private ZonedDateTime orderTime;

  @Column(updatable = false)
  private ZonedDateTime createdAt;

  @PrePersist
  public void prePersist() {
    this.createdAt = ZonedDateTime.now();
    this.status = "ORDERED";
  }

  //== 연관관계 편의 메서드 ==//
  public void addOrderItem(OrderItem orderItem) {
    orderItems.add(orderItem);
    orderItem.assignTo(this);
  }

  public void assignPayment(Payment payment) {
    this.payment = payment;
    payment.assignToOrder(this);
  }

  public void assignDeliveryInfo(DeliveryInfo deliveryInfo) {
    this.deliveryInfo = deliveryInfo;
    deliveryInfo.assignToOrder(this);
  }


  //== 생성 메서드 ==//
  public static Order create(User user, Store store, ZonedDateTime orderTime,
      List<OrderItem> orderItems, Payment payment, DeliveryInfo deliveryInfo) {

    if (user == null || store == null || orderItems == null || orderItems.isEmpty()
        || orderTime == null) { // null, isEmpty
      throw new IllegalArgumentException("필수값 누락");
    }

    String mainMenuName =
        orderItems.getFirst().getMenu().getName();
    int totalQuantity = orderItems.stream().mapToInt(OrderItem::getQuantity).sum();

    String summary = (totalQuantity > 1)
        ? String.format("%s 외 %d건", mainMenuName, totalQuantity)
        : mainMenuName;


    Order order = new Order();
    order.user = user;
    order.store = store;
    order.orderSummaryText = summary;
    order.orderTime = orderTime;
    for (OrderItem orderItem : orderItems) {
      order.addOrderItem(orderItem);
    }
    order.assignPayment(payment);
    order.assignDeliveryInfo(deliveryInfo);
    return order;
  }
}





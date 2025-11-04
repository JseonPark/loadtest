package com.example.loadtest.domain.order.repository;

import com.example.loadtest.domain.order.dto.OrderHistoryResponseDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

import lombok.RequiredArgsConstructor;

import static com.example.loadtest.domain.order.entity.QOrder.order;
import static com.example.loadtest.domain.order.entity.QOrderItem.orderItem;
import static com.example.loadtest.domain.store.entity.QMenu.menu;
import static com.example.loadtest.domain.store.entity.QStore.store;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom{

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<OrderHistoryResponseDTO> searchOrders(Long userId, String storeName, String menuName, Pageable pageable) {
    List<OrderHistoryResponseDTO> content = queryFactory
        .select(Projections.constructor(OrderHistoryResponseDTO.class,
            order.id,
            store.name,
            order.orderSummaryText,
            order.orderTime))
        .from(order)
        .join(order.store, store)
        .where(
              order.user.id.eq(userId),
              storeNameContains(storeName),
            menuNameContains(menuName)
        )
        .orderBy(order.orderTime.desc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    Long total = queryFactory
        .select(order.count())
        .from(order)
        .where(
                order.user.id.eq(userId),
                storeNameContains(storeName),
                menuNameContains(menuName)
        )
        .fetchOne();

    return new PageImpl<>(content, pageable, total != null ? total : 0L);
  }


  private BooleanExpression storeNameContains(String storeName) {
    return StringUtils.hasText(storeName) ? store.name.contains(storeName) : null;
  }

  private BooleanExpression menuNameContains(String menuName) {
    if (!StringUtils.hasText(menuName)){
      return null;
    }
    return order.id.in(
        queryFactory
            .select(orderItem.order.id)
            .from(orderItem)
            .join(orderItem.menu, menu)
            .where(menu.name.contains(menuName))
    );
  }

}

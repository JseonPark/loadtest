package com.example.loadtest.domain.order.service;

import com.example.loadtest.domain.order.dto.OrderItemDTO;
import com.example.loadtest.domain.order.dto.OrderRequestDTO;
import com.example.loadtest.domain.order.dto.OrderResponseDTO;
import com.example.loadtest.domain.order.dto.OrderSummaryDTO;
import com.example.loadtest.domain.order.entity.Order;
import com.example.loadtest.domain.order.repository.OrderRepository;
import com.example.loadtest.domain.store.entity.Menu;
import com.example.loadtest.domain.store.entity.Store;
import com.example.loadtest.domain.store.repository.MenuRepository;
import com.example.loadtest.domain.store.repository.StoreRepository;
import com.example.loadtest.domain.user.entity.User;
import com.example.loadtest.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private UserRepository userRepository;
    private StoreRepository storeRepository;
    private MenuRepository menuRepository;

    @Override
    @Transactional
    public Long createOrder(OrderRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("해당 매장이 없습니다."));

        // menuSummary를 어떻게 만들지?
        // order id를 먼저 딴 후에 orderitems를 만들어야 하나
        Order order = Order.create(user, store, dto.getOrderItems(), LocalDateTime.now());
        return 0L;
    }


    // 메뉴명 + 개수 조합한 string 반환하도록..
    @Override
    public OrderSummaryDTO createOrderItems(List<OrderItemDTO> itemDTOs) {

        OrderSummaryDTO summary = new OrderSummaryDTO();
        OrderItemDTO item = itemDTOs.get(0);
        Menu menu = menuRepository.findById(item.getMenuId())
                    .orElseThrow(() -> new IllegalArgumentException("메뉴 없음"));


        return summary;

        }



    @Override
    public List<OrderResponseDTO> getOrders(Long userId) {
        return List.of();
    }


}

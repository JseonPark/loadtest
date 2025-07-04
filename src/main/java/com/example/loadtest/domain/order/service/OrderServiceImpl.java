package com.example.loadtest.domain.order.service;

import com.example.loadtest.domain.order.dto.OrderRequestDTO;
import com.example.loadtest.domain.order.dto.OrderResponseDTO;
import com.example.loadtest.domain.order.entity.Order;
import com.example.loadtest.domain.order.repository.OrderRepository;
import com.example.loadtest.domain.store.entity.Store;
import com.example.loadtest.domain.store.repository.StoreRepository;
import com.example.loadtest.domain.user.entity.User;
import com.example.loadtest.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private UserRepository userRepository;
    private StoreRepository storeRepository;

    @Override
    public Long createOrder(OrderRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("해당 매장이 없습니다."));

        // menuSummary를 어떻게 만들지?
        Order order = Order.create(user, store, dto.getMainMenuName(), LocalDateTime.now());
        return 0L;
    }

    @Override
    public List<OrderResponseDTO> getOrders(Long userId) {
        return List.of();
    }
}

package com.example.loadtest.domain.order.service;

import com.example.loadtest.domain.order.dto.OrderItemDTO;
import com.example.loadtest.domain.order.dto.OrderRequestDTO;
import com.example.loadtest.domain.order.dto.OrderResponseDTO;
import com.example.loadtest.domain.order.entity.*;
import com.example.loadtest.domain.order.repository.OrderRepository;
import com.example.loadtest.domain.store.entity.Menu;
import com.example.loadtest.domain.store.entity.Store;
import com.example.loadtest.domain.store.repository.MenuRepository;
import com.example.loadtest.domain.store.repository.StoreRepository;
import com.example.loadtest.domain.user.entity.User;
import com.example.loadtest.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;


    @Transactional
    public long createOrder(OrderRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + dto.getUserId()));

        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("해당 매장이 없습니다. id=" + dto.getStoreId()));


        List<Long> menuIds = dto.getOrderItems().stream()
                .map(OrderItemDTO::getMenuId)
                .toList();

        Map<Long, Menu> menuMap = menuRepository.findAllById(menuIds).stream()
                .collect(Collectors.toMap(Menu::getId, Function.identity()));


        List<OrderItem> orderItems = dto.getOrderItems().stream()
                .map(itemDto -> {
                    Menu menu = menuMap.get(itemDto.getMenuId());
                    if(menu == null) {
                        throw new IllegalArgumentException("해당 메뉴가 없습니다. id=" + itemDto.getMenuId());
                    }
                    return OrderItem.create(menu, itemDto.getQuantity());
                })
                .collect(Collectors.toList());



        Payment payment = Payment.create(
                dto.getPayment().getPaymentMethod(),
                dto.getPayment().getMenuTotal(),
                dto.getPayment().getDeliveryFee(),
                dto.getPayment().getDiscountAmount()
        );

        DeliveryInfo deliveryInfo = DeliveryInfo.create(
                dto.getDelivery().getPhoneNumber(),
                dto.getDelivery().getDeliveryAddress(),
                dto.getDelivery().getMessageToRider(),
                dto.getDelivery().getMessageToStore()
        );

        Order order = Order.create(user, store, ZonedDateTime.now(), orderItems, payment, deliveryInfo);

        orderRepository.save(order);

        return order.getId();
    }


    public List<OrderResponseDTO> getOrders(Long userId) {
        return List.of();
    }
}

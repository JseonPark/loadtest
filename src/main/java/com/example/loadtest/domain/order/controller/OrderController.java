package com.example.loadtest.domain.order.controller;

import com.example.loadtest.domain.order.dto.OrderRequestDTO;
import com.example.loadtest.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequestDTO requestDTO) {
        Long orderId = orderService.createOrder(requestDTO);
        URI location = URI.create("/api/orders/" + orderId);
        return ResponseEntity.created(location).build();
    }
}

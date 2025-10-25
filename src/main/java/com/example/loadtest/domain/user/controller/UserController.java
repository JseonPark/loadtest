package com.example.loadtest.domain.user.controller;

import com.example.loadtest.domain.order.dto.OrderHistoryResponseDTO;
import com.example.loadtest.domain.order.service.OrderService;
import com.example.loadtest.domain.user.dto.UserRequestDto;
import com.example.loadtest.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserRequestDto dto) {
        userService.createUser(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<Page<OrderHistoryResponseDTO>> getOrdersByUserId(@PathVariable Long userId, Pageable pageable) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId, pageable));
    }

}

package com.example.loadtest.domain.user.controller;

import com.example.loadtest.domain.user.dto.UserRequestDto;
import com.example.loadtest.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserRequestDto dto) {
        userService.createUser(dto);
        return ResponseEntity.ok().build();
    }

}

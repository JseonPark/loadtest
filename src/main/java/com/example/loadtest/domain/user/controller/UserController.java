package com.example.loadtest.domain.user.controller;

import com.example.loadtest.domain.user.dto.UserRequestDto;
import com.example.loadtest.domain.user.entity.User;
import com.example.loadtest.domain.user.repository.UserRepository;
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

    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserRequestDto dto) {
        User user = new User(null, dto.getName(), dto.getPhone());
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

}

package com.example.loadtest.domain.user.service;

import com.example.loadtest.domain.user.dto.UserRequestDto;
import com.example.loadtest.domain.user.entity.User;
import com.example.loadtest.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void createUser(UserRequestDto dto) {
        User user = User.create(dto.getName(),dto.getPhone(),dto.getEmail());
        userRepository.save(user);
    }

}

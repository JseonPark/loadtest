package com.example.loadtest.domain.user.repository;

import com.example.loadtest.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}

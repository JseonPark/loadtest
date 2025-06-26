package com.example.loadtest.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 숫자 증가
    private Long id;

    private String name;
    private String phone;

    public static User create(String name, String phone) {
        User user = new User();
        user.name = name;
        user.phone = phone;
        return user;
    }
}

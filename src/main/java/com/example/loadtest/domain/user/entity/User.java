package com.example.loadtest.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;

    @Column(updatable = false)
    private ZonedDateTime createdAt;


    @PrePersist
    public void prePersist() {
        this.createdAt = ZonedDateTime.now();
    }

    public static User create(String name, String phone, String email) {
        User user = new User();
        user.name = name;
        user.phone = phone;
        user.email = email;
        return user;
    }
}

package com.example.loadtest.domain.store.repository;

import com.example.loadtest.domain.store.entity.Menu;
import com.example.loadtest.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}

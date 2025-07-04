package com.example.loadtest.domain.store.repository;

import com.example.loadtest.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}

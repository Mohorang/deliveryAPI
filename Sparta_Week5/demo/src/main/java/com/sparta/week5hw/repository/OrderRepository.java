package com.sparta.week5hw.repository;

import com.sparta.week5hw.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}

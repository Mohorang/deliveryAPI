package com.sparta.week5hw.repository;

import com.sparta.week5hw.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByName(String name);
    List<Menu> findByRestaurantId(Long restaurantId);
}

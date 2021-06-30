package com.max.shop.repository;

import com.max.shop.entity.stat.OrderStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface OrderStatRepository extends JpaRepository<OrderStat, Long> {

    Optional<OrderStat> findByUserIdAndDateViews(Long userId, LocalDate date);
}

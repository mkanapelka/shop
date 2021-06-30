package com.max.shop.repository;

import com.max.shop.entity.stat.OrderStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.Optional;

public interface OrderStatRepository extends JpaRepository<OrderStat, Long>, JpaSpecificationExecutor<OrderStat> {

    Optional<OrderStat> findByUserIdAndDateViews(Long userId, LocalDate date);
}

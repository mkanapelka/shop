package com.max.shop.repository;

import com.max.shop.entity.UserStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface UserStatRepository extends JpaRepository<UserStat, Long>{

    Optional<UserStat> findByUserIdAndProductIdAndDateViews(Long userId, Long productId, LocalDate date);
}

package com.max.shop.repository;

import com.max.shop.entity.stat.UserStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.Optional;

public interface UserStatRepository extends JpaRepository<UserStat, Long>, JpaSpecificationExecutor<UserStat> {

    Optional<UserStat> findByUserIdAndProductIdAndDateViews(Long userId, Long productId, LocalDate date);
}

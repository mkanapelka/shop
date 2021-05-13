package com.example.shop.repository;

import com.example.shop.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankRepository extends JpaRepository<Rank,Long> {

    public Rank findRanksById(Long id);
}

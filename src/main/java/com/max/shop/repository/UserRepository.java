package com.max.shop.repository;

import com.max.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
//    Optional<User> findByName(String source);

    Optional<User> findByUsername(String source);

    Optional<User> findByEmail(String email);
}

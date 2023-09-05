package com.dhbinh.restaurantservice.base.security.repository;

import com.dhbinh.restaurantservice.base.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String email);
}

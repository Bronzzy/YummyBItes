package com.dhbinh.yummybites.restaurant.repository;

import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
    Optional<Restaurant> findByNameIgnoreCase(String name);
}

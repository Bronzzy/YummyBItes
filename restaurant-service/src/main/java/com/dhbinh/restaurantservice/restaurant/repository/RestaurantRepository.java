package com.dhbinh.restaurantservice.restaurant.repository;

import com.dhbinh.restaurantservice.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByNameIgnoreCase(String name);

    Optional<Restaurant> findByAddressIgnoreCase(String address);
}

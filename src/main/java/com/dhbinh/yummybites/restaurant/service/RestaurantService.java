package com.dhbinh.yummybites.restaurant.service;

import com.dhbinh.yummybites.base.exception.InputValidationException;
import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import com.dhbinh.yummybites.restaurant.repository.RestaurantRepository;
import com.dhbinh.yummybites.restaurant.service.dto.RestaurantDTO;
import com.dhbinh.yummybites.restaurant.service.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantMapper restaurantMapper;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {

        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDTO.getName())
                .address(restaurantDTO.getAddress())
                .phone(restaurantDTO.getPhone())
                .openHour(restaurantDTO.getOpenHour())
                .closingHour(restaurantDTO.getClosingHour())
                .build();

        return restaurantMapper.toDTO(restaurantRepository.save(restaurant));
    }

    public Restaurant findByName(String name) {
        return restaurantRepository.findByName(name)
                .orElseThrow(() -> new InputValidationException("Restaurant not found"));
    }
}

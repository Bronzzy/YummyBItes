package com.dhbinh.yummybites.restaurant.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
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
                    .name(restaurantDTO.getName().trim())
                    .address(restaurantDTO.getAddress().trim())
                    .phone(restaurantDTO.getPhone().trim())
                    .openHour(restaurantDTO.getOpenHour())
                    .closingHour(restaurantDTO.getClosingHour())
                    .build();

            return restaurantMapper.toDTO(restaurantRepository.save(restaurant));
    }

    public Restaurant findByNameIgnoreCase(String name) {
        return restaurantRepository.findByNameIgnoreCase(name.trim())
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_RESTAURANT_NOT_FOUND,
                        ErrorMessage.RESTAURANT_NOT_FOUND));
    }
}

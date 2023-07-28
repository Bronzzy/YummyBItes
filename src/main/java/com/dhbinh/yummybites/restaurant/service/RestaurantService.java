package com.dhbinh.yummybites.restaurant.service;

import com.dhbinh.yummybites.restaurant.dao.RestaurantDAO;
import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import com.dhbinh.yummybites.restaurant.service.mapper.RestaurantMapper;
import com.dhbinh.yummybites.restaurant.service.model.RestaurantDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RestaurantService {

    @Inject
    private RestaurantDAO restaurantDAO;

    @Inject
    private RestaurantMapper restaurantMapper;

    public RestaurantDTO create(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDTO.getName())
                .phone(restaurantDTO.getPhone())
                .address(restaurantDTO.getAddress())
                .openHour(restaurantDTO.getOpenHour())
                .closingHour(restaurantDTO.getClosingHour())
                .build();
        return restaurantMapper.toDTO(restaurantDAO.create(restaurant));
    }
}

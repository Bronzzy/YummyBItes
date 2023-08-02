package com.dhbinh.yummybites.restaurant.controller;


import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import com.dhbinh.yummybites.restaurant.service.RestaurantService;
import com.dhbinh.yummybites.restaurant.service.dto.RestaurantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/restaurants")
public class RestaurantResource {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO){
        RestaurantDTO dto = restaurantService.createRestaurant(restaurantDTO);
        return ResponseEntity.created(URI.create("/api/restaurants" + dto.getId())).body(dto);
    }

    @GetMapping(value = "/find-by-name")
    public ResponseEntity<Restaurant> findByName(@RequestParam("name") String name){
        return ResponseEntity.ok(restaurantService.findByName(name));
    }
}

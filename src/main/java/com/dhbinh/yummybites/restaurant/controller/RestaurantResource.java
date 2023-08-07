package com.dhbinh.yummybites.restaurant.controller;


import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import com.dhbinh.yummybites.restaurant.service.RestaurantService;
import com.dhbinh.yummybites.restaurant.service.dto.RestaurantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Validated
@RequestMapping(value = "/restaurants")
@PreAuthorize("hasRole('OWNER')")
public class RestaurantResource {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO dto = restaurantService.createRestaurant(restaurantDTO);
        return ResponseEntity.created(URI.create("/api/restaurants" + dto.getId())).body(dto);
    }

    @GetMapping(value = "/find-by-name")
    public ResponseEntity<Restaurant> findByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(restaurantService.findByNameIgnoreCase(name));
    }
}

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
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/restaurants")
@PreAuthorize("hasRole('OWNER')")
public class RestaurantResource {

    @Autowired
    private RestaurantService restaurantService;

//    @PreAuthorize("hasRole('COOK') or hasRole('OWNER')")
    @PreAuthorize("hasAnyRole('COOK', 'OWNER')")
    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO dto = restaurantService.createRestaurant(restaurantDTO);
        return ResponseEntity.created(URI.create("/api/restaurants" + dto.getId())).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> findAll() {
        return ResponseEntity.ok().body(restaurantService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestaurantDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(restaurantService.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable("id") Long id,
                                                          @RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO dto = restaurantService.update(id, restaurantDTO);
        return ResponseEntity.ok().body(dto);
    }
}

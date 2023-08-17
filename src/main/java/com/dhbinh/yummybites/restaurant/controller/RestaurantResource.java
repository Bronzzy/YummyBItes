package com.dhbinh.yummybites.restaurant.controller;


import com.dhbinh.yummybites.restaurant.service.RestaurantService;
import com.dhbinh.yummybites.restaurant.service.dto.RestaurantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

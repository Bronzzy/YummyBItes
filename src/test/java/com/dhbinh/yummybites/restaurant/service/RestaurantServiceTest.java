package com.dhbinh.yummybites.restaurant.service;

import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import com.dhbinh.yummybites.restaurant.repository.RestaurantRepository;
import com.dhbinh.yummybites.restaurant.service.dto.RestaurantDTO;
import com.dhbinh.yummybites.restaurant.service.mapper.RestaurantMapper;
import com.dhbinh.yummybites.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantMapper restaurantMapper;

    @Autowired
    private Utils utils;


    private Restaurant validRestaurant() {
        return Restaurant.builder()
                .id(1L)
                .name("YummyBites")
                .address("4089 Charing Cross Drive")
                .phone("341-724-5075")
                .openHour(LocalTime.of(11, 0, 0))
                .closingHour(LocalTime.of(22, 0, 0))
                .build();
    }

    @Test
    void createRestaurant_WithMandatoryFields_ReturnDTO() {
        Restaurant restaurant = validRestaurant();

        RestaurantDTO dto = RestaurantDTO.builder()
                .name("YummyBites")
                .address("4089 Charing Cross Drive")
                .phone("341-724-5075")
                .openHour(LocalTime.of(11, 0, 0))
                .closingHour(LocalTime.of(22, 0, 0))
                .build();

        when(restaurantRepository.save(any())).thenReturn(restaurant);
        when(restaurantMapper.toDTO(restaurant)).thenReturn(dto);

        RestaurantDTO result = restaurantService.createRestaurant(dto);

        assertEquals(result.getName(), dto.getName());
        assertEquals(result.getAddress(), dto.getAddress());
        assertEquals(result.getPhone(), dto.getPhone());
        assertEquals(result.getOpenHour(), dto.getOpenHour());
        assertEquals(result.getClosingHour(), dto.getClosingHour());
    }

    @Test
    public void findAll_AvailableRestaurant_ReturnModelList() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant());
        restaurants.add(new Restaurant());

        when(restaurantRepository.findAll()).thenReturn(restaurants);

        List<RestaurantDTO> expectedDTOs = new ArrayList<>();
        expectedDTOs.add(new RestaurantDTO());
        expectedDTOs.add(new RestaurantDTO());

        when(restaurantMapper.toDTOList(restaurants)).thenReturn(expectedDTOs);

        List<RestaurantDTO> actualDTOs = restaurantService.findAll();

        assertEquals(expectedDTOs, actualDTOs);
    }

    @Test
    public void findRestaurant_ExistedId_DTO(){
        Restaurant restaurant = validRestaurant();

        RestaurantDTO dto = RestaurantDTO.builder()
                .name("YummyBites")
                .address("4089 Charing Cross Drive")
                .phone("341-724-5075")
                .openHour(LocalTime.of(11, 0, 0))
                .closingHour(LocalTime.of(22, 0, 0))
                .build();

        when(restaurantRepository.findById(1L)).thenReturn(java.util.Optional.of(restaurant));
        when(restaurantMapper.toDTO(restaurant)).thenReturn(dto);

        RestaurantDTO actualDTO = restaurantService.findById(1L);

        assertEquals(dto, actualDTO);
    }

    @Test
    public void findRestaurant_NonExistedId_ThrowException() throws ResourceNotFoundException{
        when(restaurantRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> restaurantService.findById(999L));
    }

    @Test
    public void findRestaurantNameIgnoreCase_ExistedRestaurant_ReturnModel() {
        String name = "Restaurant Name";
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);

        when(restaurantRepository.findByNameIgnoreCase(name)).thenReturn(Optional.of(restaurant));

        Restaurant result = restaurantService.findByNameIgnoreCase(name);

        assertEquals(restaurant, result);
    }

    @Test
    public void findRestaurantNameIgnoreCase_NonExistedRestaurant_ThrowException() {
        String name = "Non-existent Restaurant Name";

        when(restaurantRepository.findByNameIgnoreCase(name)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> restaurantService.findByNameIgnoreCase(name));
    }
}




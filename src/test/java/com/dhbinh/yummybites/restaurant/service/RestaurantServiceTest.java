package com.dhbinh.yummybites.restaurant.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.InputValidationException;
import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import com.dhbinh.yummybites.restaurant.repository.RestaurantRepository;
import com.dhbinh.yummybites.restaurant.service.dto.RestaurantDTO;
import com.dhbinh.yummybites.restaurant.service.mapper.RestaurantMapper;
import com.dhbinh.yummybites.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    public void testFindAll_Positive() {
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
}

//    @ParameterizedTest
//    @NullAndEmptySource
//    @ValueSource(strings = {" "})
//    void createRestaurant_WithNameNullOrBlank_ReturnErrorMessage(String name) throws InputValidationException {
//        RestaurantDTO dto = RestaurantDTO.builder()
//                .name(name)
//                .build();
//
//        assertThrows(InputValidationException.class, () -> restaurantService.createRestaurant(dto));
//    }
//
//    @ParameterizedTest
//    @NullAndEmptySource
//    @ValueSource(strings = {" "})
//    void createRestaurant_WithAddressNullOrBlank_ReturnErrorMessage(String address) throws InputValidationException {
//        RestaurantDTO dto = RestaurantDTO.builder()
//                .address(address)
//                .build();
//
//        assertThrows(InputValidationException.class, () -> restaurantService.createRestaurant(dto));
//    }
//
//    @ParameterizedTest
//    @NullAndEmptySource
//    @ValueSource(strings = {" "})
//    void createRestaurant_WithPhoneNullOrBlank_ReturnErrorMessage(String phone)  {
//        RestaurantDTO dto = RestaurantDTO.builder()
//                .phone(phone)
//                .build();
//
//        assertThrows(InputValidationException.class, () -> restaurantService.createRestaurant(dto));
//    }
//
//
//    @ParameterizedTest
//    @ValueSource(strings = {"341-724-5075abc", "341-724-5075!#~!@"})
//    void createRestaurant_WithPhoneWrongFormat_ReturnErrorMessage(String phone) throws InputValidationException {
//        RestaurantDTO dto = RestaurantDTO.builder()
//                .phone(phone)
//                .build();
//
//        assertThrows(InputValidationException.class, () -> restaurantService.createRestaurant(dto));
//    }
//
//    @Test
//    void createRestaurant_WithOpenHourNull_ReturnErrorMessage(){
//        RestaurantDTO dto = RestaurantDTO.builder()
//                .openHour(null)
//                .build();
//
//        assertThrows(InputValidationException.class, () -> restaurantService.createRestaurant(dto));
//    }
//
//    @Test
//    void createRestaurant_WithClosingHourNull_ReturnErrorMessage(){
//        RestaurantDTO dto = RestaurantDTO.builder()
//                .closingHour(null)
//                .build();
//
//        assertThrows(InputValidationException.class, () -> restaurantService.createRestaurant(dto));
//    }



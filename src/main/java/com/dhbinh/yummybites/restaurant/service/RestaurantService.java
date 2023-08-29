package com.dhbinh.yummybites.restaurant.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.InputValidationException;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import com.dhbinh.yummybites.restaurant.repository.RestaurantRepository;
import com.dhbinh.yummybites.restaurant.service.dto.RestaurantDTO;
import com.dhbinh.yummybites.restaurant.service.mapper.RestaurantMapper;
import com.dhbinh.yummybites.utils.CommonConstant;
import com.dhbinh.yummybites.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    @Autowired
    private RestaurantMapper restaurantMapper;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private Utils utils;

    private static final Logger logger = LoggerFactory.getLogger(Restaurant.class);

    public Restaurant createRestaurant(RestaurantDTO restaurantDTO) {
        verifyAndModify(restaurantDTO);

        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDTO.getName())
                .address(restaurantDTO.getAddress())
                .phone(restaurantDTO.getPhone())
                .openHour(restaurantDTO.getOpenHour())
                .closingHour(restaurantDTO.getClosingHour())
                .build();

        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Long id, RestaurantDTO restaurantDTO) {
        verifyAndModify(restaurantDTO);
        Restaurant restaurant = restaurantRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_RESTAURANT_NOT_FOUND,
                        ErrorMessage.RESTAURANT_NOT_FOUND));

        restaurantMapper.mapFromDto(restaurantDTO, restaurant);
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_RESTAURANT_NOT_FOUND,
                        ErrorMessage.RESTAURANT_NOT_FOUND));
    }

    public void verifyAndModify(RestaurantDTO restaurantDTO) {
        if (restaurantDTO.getName() != null) {
            restaurantDTO.setName(Utils.capitalizeFirstWordAndAfterWhitespace(restaurantDTO.getName().trim()));
        }

        if (restaurantDTO.getAddress() != null) {
            restaurantDTO.setAddress(Utils.capitalizeFirstWordAndAfterWhitespace(restaurantDTO.getAddress().trim()));
        }

        if (isNameExisted(restaurantDTO.getName())) {
            throw new InputValidationException(
                    ErrorMessage.KEY_RESTAURANT_NAME_ALREADY_USED,
                    ErrorMessage.RESTAURANT_NAME_ALREADY_USED);
        }

        if (isAddressAlreadyUsed(restaurantDTO.getAddress())) {
            throw new InputValidationException(
                    ErrorMessage.KEY_RESTAURANT_ADDRESS_ALREADY_USED,
                    ErrorMessage.RESTAURANT_ADDRESS_ALREADY_USED);
        }

        if (!isPhoneValidFormat(restaurantDTO.getPhone())) {
            throw new InputValidationException(
                    ErrorMessage.KEY_PHONE_WRONG_FORMAT,
                    ErrorMessage.PHONE_WRONG_FORMAT);
        }
    }

    public boolean isNameExisted(String name) {
        boolean isNameExisted = false;
        if (name != null)
            isNameExisted = restaurantRepository.findByNameIgnoreCase(name.trim()).isPresent();
        return isNameExisted;
    }

    private boolean isAddressAlreadyUsed(String address) {
        boolean isAddressAlreadyUsed = false;
        if (address != null)
            isAddressAlreadyUsed = restaurantRepository.findByAddressIgnoreCase(address.trim()).isPresent();
        return isAddressAlreadyUsed;
    }

    private boolean isPhoneValidFormat(String phone) {
        boolean isPhoneWrongFormat = false;
        String pattern = CommonConstant.PHONE_NUMBER_PATTERN;
        if (phone != null)
            isPhoneWrongFormat = phone.matches(pattern);

        return isPhoneWrongFormat;
    }

    public Restaurant findByNameIgnoreCase(String name) {
        return restaurantRepository.findByNameIgnoreCase(name.trim()).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_RESTAURANT_NOT_FOUND,
                        ErrorMessage.RESTAURANT_NOT_FOUND));
    }
}

package com.dhbinh.yummybites.restaurant.service.mapper;

import com.dhbinh.yummybites.base.mapper.BaseMapper;
import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import com.dhbinh.yummybites.restaurant.service.model.RestaurantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RestaurantMapper extends BaseMapper<Restaurant, RestaurantDTO> {

    @Override
    @Mapping(source = "openHour", target = "openHour", dateFormat = "HH:mm:ss")
    @Mapping(source = "closingHour", target = "closingHour", dateFormat = "HH:mm:ss")
    RestaurantDTO toDTO(Restaurant entity);
}

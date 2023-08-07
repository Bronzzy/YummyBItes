package com.dhbinh.yummybites.restaurant.service.mapper;


import com.dhbinh.yummybites.base.mapper.BaseMapper;
import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import com.dhbinh.yummybites.restaurant.service.dto.RestaurantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RestaurantMapper extends BaseMapper<Restaurant, RestaurantDTO> {

}

package com.dhbinh.yummybites.restaurant.service.mapper;

import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import com.dhbinh.yummybites.restaurant.service.model.RestaurantDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-28T17:38:38+0700",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@ApplicationScoped
public class RestaurantMapperImpl implements RestaurantMapper {

    @Override
    public RestaurantDTO toDTO(Restaurant entity) {
        if ( entity == null ) {
            return null;
        }

        RestaurantDTO.RestaurantDTOBuilder restaurantDTO = RestaurantDTO.builder();

        restaurantDTO.name( entity.getName() );
        restaurantDTO.address( entity.getAddress() );
        restaurantDTO.phone( entity.getPhone() );
        restaurantDTO.openHour( entity.getOpenHour() );
        restaurantDTO.closingHour( entity.getClosingHour() );

        return restaurantDTO.build();
    }

    @Override
    public List<RestaurantDTO> toDTOList(List<Restaurant> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RestaurantDTO> list = new ArrayList<RestaurantDTO>( entityList.size() );
        for ( Restaurant restaurant : entityList ) {
            list.add( toDTO( restaurant ) );
        }

        return list;
    }

    @Override
    public Restaurant toEntity(RestaurantDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Restaurant.RestaurantBuilder restaurant = Restaurant.builder();

        restaurant.name( dto.getName() );
        restaurant.address( dto.getAddress() );
        restaurant.phone( dto.getPhone() );
        restaurant.openHour( dto.getOpenHour() );
        restaurant.closingHour( dto.getClosingHour() );

        return restaurant.build();
    }

    @Override
    public List<Restaurant> toEntityList(List<RestaurantDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Restaurant> list = new ArrayList<Restaurant>( dtoList.size() );
        for ( RestaurantDTO restaurantDTO : dtoList ) {
            list.add( toEntity( restaurantDTO ) );
        }

        return list;
    }
}

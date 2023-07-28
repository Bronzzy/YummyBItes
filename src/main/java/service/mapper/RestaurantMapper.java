package service.mapper;

import entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import service.model.RestaurantDTO;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RestaurantMapper extends BaseMapper<Restaurant, RestaurantDTO> {
}

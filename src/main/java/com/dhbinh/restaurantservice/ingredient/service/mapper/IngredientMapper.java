package com.dhbinh.restaurantservice.ingredient.service.mapper;

import com.dhbinh.restaurantservice.base.mapper.BaseMapper;
import com.dhbinh.restaurantservice.ingredient.entity.Ingredient;
import com.dhbinh.restaurantservice.ingredient.service.dto.IngredientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IngredientMapper extends BaseMapper<Ingredient, IngredientDTO> {

    @Mapping(target = "restaurantName", source = "restaurant.name")
    IngredientDTO toDTO(Ingredient ingredient);
}

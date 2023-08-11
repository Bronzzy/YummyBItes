package com.dhbinh.yummybites.ingredients.service.mapper;

import com.dhbinh.yummybites.base.mapper.BaseMapper;
import com.dhbinh.yummybites.ingredients.entity.Ingredient;
import com.dhbinh.yummybites.ingredients.service.dto.IngredientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IngredientMapper extends BaseMapper<Ingredient, IngredientDTO> {

    @Mapping(target = "restaurantName", source = "restaurant.name")
    IngredientDTO toDTO(Ingredient ingredient);
}

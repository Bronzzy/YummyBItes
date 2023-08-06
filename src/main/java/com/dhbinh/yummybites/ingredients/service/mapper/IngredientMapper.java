package com.dhbinh.yummybites.ingredients.service.mapper;

import com.dhbinh.yummybites.base.mapper.BaseMapper;
import com.dhbinh.yummybites.ingredients.entity.Ingredient;
import com.dhbinh.yummybites.ingredients.service.dto.IngredientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IngredientMapper extends BaseMapper<Ingredient, IngredientDTO> {

    @Mapping(target = "restaurantName", source = "restaurant.name")
    IngredientDTO toDTO(Ingredient ingredient);
}

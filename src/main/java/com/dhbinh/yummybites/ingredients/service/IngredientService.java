package com.dhbinh.yummybites.ingredients.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.InputValidationException;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.ingredients.entity.Ingredient;
import com.dhbinh.yummybites.ingredients.repository.IngredientRepository;
import com.dhbinh.yummybites.ingredients.service.dto.IngredientDTO;
import com.dhbinh.yummybites.ingredients.service.mapper.IngredientMapper;
import com.dhbinh.yummybites.restaurant.service.RestaurantService;
import com.dhbinh.yummybites.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RestaurantService restaurantService;

    private final IngredientMapper ingredientMapper;

    private final Utils utils;

    public IngredientDTO create(IngredientDTO ingredientDTO) {
        verify(ingredientDTO);

        Ingredient ingredient = Ingredient.builder()
                .name(ingredientDTO.getName().trim())
                .quantity(ingredientDTO.getQuantity())
                .restaurant(restaurantService.findByNameIgnoreCase(ingredientDTO.getRestaurantName().trim()))
                .build();
        return ingredientMapper.toDTO(ingredientRepository.save(ingredient));
    }

    public IngredientDTO add(Long ID, Double quantity) {
        Ingredient ingredient = ingredientRepository.findById(ID).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_INGREDIENT_NOT_FOUND,
                        ErrorMessage.INGREDIENT_NOT_FOUND));

        ingredient.setQuantity(ingredient.getQuantity() + quantity);
        return ingredientMapper.toDTO(ingredientRepository.save(ingredient));
    }

    public IngredientDTO deducted(Long ID, Double quantity) {
        Ingredient ingredient = ingredientRepository.findById(ID).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_INGREDIENT_NOT_FOUND,
                        ErrorMessage.INGREDIENT_NOT_FOUND));

        ingredient.setQuantity(ingredient.getQuantity() - quantity);
        return ingredientMapper.toDTO(ingredientRepository.save(ingredient));
    }

    public void verify(IngredientDTO ingredientDTO) {
        if (ingredientDTO.getName() != null) {
            ingredientDTO.setName(utils.capitalizeFirstWordAndAfterWhitespace(ingredientDTO.getName().trim()));
        }

        if (isIngredientExist(ingredientDTO.getName())) {
            throw new InputValidationException(
                    ErrorMessage.KEY_INGREDIENT_ALREADY_EXIST,
                    ErrorMessage.INGREDIENT_ALREADY_EXIST);
        }
    }

    public boolean isIngredientExist(String name) {
        boolean isIngredientExist = false;
        if (name != null) {
            isIngredientExist = ingredientRepository.findByNameIgnoreCase(name.trim()).isPresent();
        }
        return isIngredientExist;
    }
}

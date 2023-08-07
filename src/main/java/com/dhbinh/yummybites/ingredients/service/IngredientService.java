package com.dhbinh.yummybites.ingredients.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.InputValidationException;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.ingredients.entity.Ingredient;
import com.dhbinh.yummybites.ingredients.repository.IngredientRepository;
import com.dhbinh.yummybites.ingredients.service.dto.IngredientDTO;
import com.dhbinh.yummybites.ingredients.service.mapper.IngredientMapper;
import com.dhbinh.yummybites.restaurant.service.RestaurantService;
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

    public IngredientDTO create(IngredientDTO ingredientDTO) {
        verifyIngredient(ingredientDTO);

        Ingredient ingredient = Ingredient.builder()
                .name(ingredientDTO.getName().trim())
                .quantity(ingredientDTO.getQuantity())
                .restaurant(restaurantService.findByNameIgnoreCase(ingredientDTO.getRestaurantName().trim()))
                .build();
        return ingredientMapper.toDTO(ingredientRepository.save(ingredient));
    }

    public IngredientDTO add(Long ID, Double quantity) {
        Ingredient ingredient = ingredientRepository.findById(ID).
                orElseThrow(() -> new ResourceNotFoundException
                        (ErrorMessage.INGREDIENT_NOT_FOUND, ErrorMessage.KEY_INGREDIENT_NOT_FOUND));
        ingredient.setQuantity(ingredient.getQuantity() + quantity);
        return ingredientMapper.toDTO(ingredientRepository.save(ingredient));
    }

    public IngredientDTO deducted(Long ID, Double quantity) {
        Ingredient ingredient = ingredientRepository.findById(ID).
                orElseThrow(() -> new ResourceNotFoundException
                        (ErrorMessage.INGREDIENT_NOT_FOUND, ErrorMessage.KEY_INGREDIENT_NOT_FOUND));
        ingredient.setQuantity(ingredient.getQuantity() - quantity);
        return ingredientMapper.toDTO(ingredientRepository.save(ingredient));
    }

    public void verifyIngredient(IngredientDTO ingredientDTO) {
        if (isIngredientExist(ingredientDTO.getName().trim())) {
            throw new InputValidationException
                    (ErrorMessage.KEY_INGREDIENT_ALREADY_EXIST, ErrorMessage.INGREDIENT_ALREADY_EXIST);
        }
    }

    public boolean isIngredientExist(String name) {
        return ingredientRepository.findByNameIgnoreCase(name.trim()) != null;
    }
}

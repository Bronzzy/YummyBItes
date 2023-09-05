package com.dhbinh.restaurantservice.ingredient.service;

import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.base.exception.InputValidationException;
import com.dhbinh.restaurantservice.base.exception.ResourceNotFoundException;
import com.dhbinh.restaurantservice.ingredient.entity.Ingredient;
import com.dhbinh.restaurantservice.ingredient.repository.IngredientRepository;
import com.dhbinh.restaurantservice.ingredient.service.dto.IngredientDTO;
import com.dhbinh.restaurantservice.ingredient.service.mapper.IngredientMapper;
import com.dhbinh.restaurantservice.ingredient.specification.IngredientSpecification;
import com.dhbinh.restaurantservice.restaurant.service.RestaurantService;
import com.dhbinh.restaurantservice.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private IngredientMapper ingredientMapper;

    @Autowired
    private Utils utils;

    public IngredientDTO create(IngredientDTO ingredientDTO) {
        verifyAndModify(ingredientDTO);

        Ingredient ingredient = Ingredient.builder()
                .name(ingredientDTO.getName().trim())
                .quantity(ingredientDTO.getQuantity())
                .restaurant(restaurantService.findByNameIgnoreCase(ingredientDTO.getRestaurantName().trim()))
                .build();
        return ingredientMapper.toDTO(ingredientRepository.save(ingredient));
    }

    public List<IngredientDTO> findAll() {
        return ingredientMapper.toDTOList(ingredientRepository.findAll());
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

    public List<IngredientDTO> findWithSpecifications(String name, double quantityString) {
        Specification<Ingredient> spec = IngredientSpecification.findWithSpecifications(name, quantityString);
        return ingredientMapper.toDTOList(ingredientRepository.findAll(spec));
    }

    public void verifyAndModify(IngredientDTO ingredientDTO) {
        if (ingredientDTO.getName() != null) {
            ingredientDTO.setName(Utils.capitalizeFirstWordAndAfterWhitespace(ingredientDTO.getName().trim()));
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

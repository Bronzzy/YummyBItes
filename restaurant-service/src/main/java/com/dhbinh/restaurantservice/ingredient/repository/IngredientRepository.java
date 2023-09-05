package com.dhbinh.restaurantservice.ingredient.repository;

import com.dhbinh.restaurantservice.ingredient.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>, JpaSpecificationExecutor<Ingredient> {
    Optional<Ingredient> findByNameIgnoreCase(String name);

}

package com.dhbinh.yummybites.ingredients.repository;

import com.dhbinh.yummybites.ingredients.entity.Ingredient;
import org.springframework.data.jpa.domain.Specification;

public class IngredientSpecification {

    public static Specification<Ingredient> withQuantityLessThanOrEqualTo(double quantity) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("quantity"), quantity);
        });
    }

    public static Specification<Ingredient> withNameIgnoreCae(String name) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase().trim()+ "%");
        });
    }
}

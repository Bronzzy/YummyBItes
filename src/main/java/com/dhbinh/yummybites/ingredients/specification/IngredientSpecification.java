package com.dhbinh.yummybites.ingredients.specification;

import com.dhbinh.yummybites.ingredients.entity.Ingredient;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class IngredientSpecification {
    public static Specification<Ingredient> findWithNameAndQuantitySpecification(String name, String quantity) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (!name.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (!quantity.isEmpty()) {
                double quantityValue = Double.parseDouble(quantity);
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("quantity"), quantityValue));
            }
            return predicate;
        };
    }
}

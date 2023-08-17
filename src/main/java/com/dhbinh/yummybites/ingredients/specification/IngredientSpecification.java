package com.dhbinh.yummybites.ingredients.specification;

import com.dhbinh.yummybites.ingredients.entity.Ingredient;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class IngredientSpecification {
    public static Specification<Ingredient> findWithSpecifications(String name, double quantity) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (!name.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (quantity > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("quantity"), quantity));
            }
            return predicate;
        };
    }
}

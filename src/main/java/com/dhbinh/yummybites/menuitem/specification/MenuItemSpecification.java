package com.dhbinh.yummybites.menuitem.specification;

import com.dhbinh.yummybites.menuitem.entity.MenuItem;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class MenuItemSpecification {
    public static Specification<MenuItem> findWithSpecification(String name, double priceLessThan, double priceGreaterThan, String type) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (!name.isEmpty()) {
                Predicate namePredicate = criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase().trim() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + name.toLowerCase().trim() + "%")
                );
                predicate = criteriaBuilder.and(predicate, namePredicate);
            }

            if (priceLessThan > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceLessThan));
            }

            if (priceGreaterThan > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceGreaterThan));
            }

            if (!type.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("dishType").as(String.class)), "%" + type.toLowerCase().trim() + "%"));
            }
            return predicate;
        };
    }
}

package com.dhbinh.yummybites.menuitem.specification;

import com.dhbinh.yummybites.menuitem.entity.MenuItem;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class MenuItemSpecification {
//    public static Specification<MenuItem> findWithSpecification(String name, String priceLessThan, String priceGreaterThan, String type) {
//        return (root, query, criteriaBuilder) -> {
//            Predicate predicate = criteriaBuilder.conjunction();
//
//            if(!name.isEmpty()){
//                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase().trim() + "%"));
//            }
//
//            if(!priceLessThan.isEmpty()){
//                double priceLessThanValue = Double.parseDouble(priceLessThan);
//                predicate = criteriaBuilder.and(predicate,criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceLessThanValue));
//            }
//
//            if(!priceGreaterThan.isEmpty()){
//                double priceGreaterThanValue = Double.parseDouble(priceGreaterThan);
//                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("price"),priceGreaterThanValue));
//            }
//
//            if()
//        }
//    }
}

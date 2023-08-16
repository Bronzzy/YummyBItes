package com.dhbinh.yummybites.bill.specification;

import com.dhbinh.yummybites.bill.entity.Bill;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class BillSpecification {
    public static Specification<Bill> findWithSpecification(String day, String month, String year, double priceLessThan, double priceGreaterThan) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (!day.isEmpty()) {
                int dayValue = Integer.parseInt(day);
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(criteriaBuilder.function("day", String.class, root.get("createdDate")), dayValue));
            }

            if (!month.isEmpty()) {
                int monthValue = Integer.parseInt(month);
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(criteriaBuilder.function("month", String.class, root.get("createdDate")), monthValue));
            }

            if (!year.isEmpty()) {
                int yearValue = Integer.parseInt(year);
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(criteriaBuilder.function("year", String.class, root.get("createdDate")), yearValue));
            }

            if(priceLessThan > 0){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("totalPrice"), priceLessThan));
            }

            if(priceGreaterThan > 0){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("totalPrice"), priceGreaterThan));
            }
            return predicate;
        };
    }
}


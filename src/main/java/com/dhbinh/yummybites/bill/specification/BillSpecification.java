package com.dhbinh.yummybites.bill.specification;

import com.dhbinh.yummybites.bill.entity.Bill;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class BillSpecification {
    public static Specification<Bill> findWithDateSpecification(String day, String month, String year, String priceLessThan, String priceGreaterThan) {
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
            if (!priceLessThan.isEmpty()) {
                double priceLessThanValue = Double.parseDouble(priceLessThan);
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("totalPrice"), priceLessThanValue));
            }
            if(!priceGreaterThan.isEmpty()){
                double priceGreaterThanValue = Double.parseDouble(priceGreaterThan);
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("totalPrice"), priceGreaterThanValue));
            }
            return predicate;
        };
    }
}


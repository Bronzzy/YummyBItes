package com.dhbinh.restaurantservice.order.specification;

import com.dhbinh.restaurantservice.diningtable.entity.DiningTable;
import com.dhbinh.restaurantservice.employee.entity.Employee;
import com.dhbinh.restaurantservice.order.entity.Order;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

public class OrderSpecification {

    public static Specification<Order> findWithSpecifications(String employeeName, int tableNumber, double priceLessThan, double priceGreaterThan, int day, int month, int year) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            Join<Order, Employee> employeeJoin = root.join("employee");
            Join<Order, DiningTable> diningTableJoin = root.join("diningTable");

            if (!employeeName.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(employeeJoin.get("lastName")), "%" + employeeName.toLowerCase().trim() + "%"));
            }

            if (tableNumber > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(diningTableJoin.get("number"), tableNumber));
            }

            if (priceLessThan > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("totalPrice"), priceLessThan));
            }

            if (priceGreaterThan > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("totalPrice"), priceGreaterThan));
            }

            if (day > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(criteriaBuilder.function("day", Integer.class, root.get("createdDate")), day));
            }

            if (month > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(criteriaBuilder.function("month", Integer.class, root.get("createdDate")), month));
            }

            if (year > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(criteriaBuilder.function("year", Integer.class, root.get("createdDate")), year));
            }

            return predicate;
        };
    }
}

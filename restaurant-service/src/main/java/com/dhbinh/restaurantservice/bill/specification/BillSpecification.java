package com.dhbinh.restaurantservice.bill.specification;

import com.dhbinh.restaurantservice.bill.entity.Bill;
import com.dhbinh.restaurantservice.employee.entity.Employee;
import com.dhbinh.restaurantservice.supplier.entity.Supplier;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
public class BillSpecification {
    public static Specification<Bill> findWithSpecifications(String employeeName, String supplierName, int day, int month, int year, double priceLessThan, double priceGreaterThan) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            Join<Bill, Employee> employeeJoin = root.join("employee");
            Join<Bill, Supplier> supplierJoin = root.join("supplier");

            if (!employeeName.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(employeeJoin.get("lastName")), "%" + employeeName.toLowerCase().trim() + "%"));
            }

            if (!supplierName.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(supplierJoin.get("name")), "%" + supplierName.toLowerCase().trim() + "%"));
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

            if (priceLessThan > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("totalPrice"), priceLessThan));
            }

            if (priceGreaterThan > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("totalPrice"), priceGreaterThan));
            }

            return predicate;
        };
    }
}


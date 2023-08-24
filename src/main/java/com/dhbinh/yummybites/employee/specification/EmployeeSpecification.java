package com.dhbinh.yummybites.employee.specification;

import com.dhbinh.yummybites.employee.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.time.Year;

public class EmployeeSpecification {
    public static Specification<Employee> findWithSpecifications(String firstName, String lastName, String address, int day, int month, int year, int ageLessThan, int ageGreaterThan) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (!firstName.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase().trim() + "%"));
            }

            if (!lastName.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase().trim() + "%"));
            }

            if (!address.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + address.toLowerCase().trim() + "%"));
            }

            if (day > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(criteriaBuilder.function("day", Integer.class, root.get("dob")), day));
            }

            if (month > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(criteriaBuilder.function("month", Integer.class, root.get("dob")), month));
            }

            if (year > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(criteriaBuilder.function("year", Integer.class, root.get("dob")), year));
            }

            if (ageLessThan > 0) {
                Expression<Integer> ageDifference = criteriaBuilder.diff(criteriaBuilder.literal(Year.now().getValue()),
                        criteriaBuilder.function("year", Integer.class, root.get("dob")));

                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(ageDifference, ageLessThan));
            }

            if (ageGreaterThan > 0) {
                Expression<Integer> ageDifference = criteriaBuilder.diff(criteriaBuilder.literal(Year.now().getValue()),
                        criteriaBuilder.function("year", Integer.class, root.get("dob")));

                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(ageDifference, ageGreaterThan));
            }

            return predicate;
        };
    }
}

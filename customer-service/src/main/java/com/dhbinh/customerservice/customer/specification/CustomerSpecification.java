package com.dhbinh.customerservice.customer.specification;

import com.dhbinh.customerservice.customer.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class CustomerSpecification {

    public static Specification<Customer> findWithSpecification(String lastName, String firstName, String email, String phone, double pointLessThan, double pointGreaterThan, String memberTier) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (!firstName.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase().trim() + "%"));
            }
            if (!lastName.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase().trim() + "%"));
            }
            if (!email.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase().trim() + "%"));
            }
            if (!phone.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")), "%" + phone.toLowerCase().trim() + "%"));
            }
            if (pointLessThan > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("loyaltyPoint"), pointLessThan));
            }
            if (pointGreaterThan > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("loyaltyPoint"), pointGreaterThan));
            }
            if (!memberTier.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("memberTier").as(String.class)), "%" + memberTier.toLowerCase().trim() + "%"));
            }
            return predicate;
        };
    }
}

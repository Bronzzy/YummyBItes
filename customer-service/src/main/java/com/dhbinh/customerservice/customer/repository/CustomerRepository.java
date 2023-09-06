package com.dhbinh.customerservice.customer.repository;

import com.dhbinh.customerservice.customer.entity.Customer;
import com.dhbinh.customerservice.customer.specification.CustomerSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<CustomerSpecification> {
    Optional<Customer> findByPhone(String phone);
}

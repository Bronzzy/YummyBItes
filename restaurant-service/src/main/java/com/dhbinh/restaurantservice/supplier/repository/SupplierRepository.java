package com.dhbinh.restaurantservice.supplier.repository;

import com.dhbinh.restaurantservice.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Optional<Supplier> findByNameIgnoreCase(String name);

    Optional<Supplier> findByAddressIgnoreCase(String address);
}

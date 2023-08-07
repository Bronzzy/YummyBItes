package com.dhbinh.yummybites.supplier.repository;

import com.dhbinh.yummybites.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}

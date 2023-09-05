package com.dhbinh.restaurantservice.diningtable.repository;

import com.dhbinh.restaurantservice.diningtable.entity.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiningTableRepository extends JpaRepository<DiningTable, Long> {
}

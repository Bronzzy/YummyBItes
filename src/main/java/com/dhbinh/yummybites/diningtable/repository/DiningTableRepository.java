package com.dhbinh.yummybites.diningtable.repository;

import com.dhbinh.yummybites.diningtable.entity.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiningTableRepository extends JpaRepository<DiningTable, Long> {
}

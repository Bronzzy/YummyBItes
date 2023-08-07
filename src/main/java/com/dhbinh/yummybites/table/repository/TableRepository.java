package com.dhbinh.yummybites.table.repository;

import com.dhbinh.yummybites.table.entity.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Tables, Long> {
}

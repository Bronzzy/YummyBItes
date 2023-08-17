package com.dhbinh.yummybites.menuitem.repository;

import com.dhbinh.yummybites.menuitem.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long>, JpaSpecificationExecutor<MenuItem> {

    Optional<MenuItem> findByNameIgnoreCase(String name);
}

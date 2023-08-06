package com.dhbinh.yummybites.menuitem.repository;

import com.dhbinh.yummybites.menuitem.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}

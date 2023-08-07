package com.dhbinh.yummybites.menuitem.repository;

import com.dhbinh.yummybites.menuitem.entity.MenuItem;
import com.dhbinh.yummybites.menuitem.service.dto.MenuItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    Optional<MenuItem> findByNameIgnoreCase(String name);
}

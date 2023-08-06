package com.dhbinh.yummybites.menuitem.service;

import com.dhbinh.yummybites.menuitem.entity.MenuItem;
import com.dhbinh.yummybites.menuitem.repository.MenuItemRepository;
import com.dhbinh.yummybites.menuitem.service.dto.MenuItemDTO;
import com.dhbinh.yummybites.menuitem.service.mapper.MenuItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    private final MenuItemMapper menuItemMapper;

    public MenuItemDTO create(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = MenuItem.builder()
                .name(menuItemDTO.getName().trim())
                .description(menuItemDTO.getDescription().trim())
                .price(menuItemDTO.getPrice())
                .dishType(menuItemDTO.getDishType())
                .build();

        return menuItemMapper.toDTO(menuItemRepository.save(menuItem));
    }
}

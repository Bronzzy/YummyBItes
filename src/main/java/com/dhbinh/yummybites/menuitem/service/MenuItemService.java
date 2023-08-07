package com.dhbinh.yummybites.menuitem.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.menuitem.entity.DishType;
import com.dhbinh.yummybites.menuitem.entity.MenuItem;
import com.dhbinh.yummybites.menuitem.repository.MenuItemRepository;
import com.dhbinh.yummybites.menuitem.service.dto.MenuItemDTO;
import com.dhbinh.yummybites.menuitem.service.mapper.MenuItemMapper;
import com.dhbinh.yummybites.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    private final MenuItemMapper menuItemMapper;

    public MenuItemDTO create(MenuItemDTO menuItemDTO) {
        String PREFIX_DISH_TYPE = "DISH_TYPE";
        MenuItem menuItem = MenuItem.builder()
                .name(menuItemDTO.getName().trim())
                .description(menuItemDTO.getDescription().trim())
                .price(menuItemDTO.getPrice())
                .dishType(DishType.valueOf(PREFIX_DISH_TYPE + menuItemDTO.getDishType().trim()))
                .restaurant(restaurantService.findByNameIgnoreCase(menuItemDTO.getRestaurantName().trim()))
                .build();

        return menuItemMapper.toDTO(menuItemRepository.save(menuItem));
    }

    public MenuItemDTO findByName(String name){
        return menuItemMapper.toDTO(menuItemRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.KEY_MENU_ITEM_NOT_FOUND,ErrorMessage.MENU_ITEM_NOT_FOUND)));
    }
}

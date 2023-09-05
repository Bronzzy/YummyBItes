package com.dhbinh.restaurantservice.menuitem.service;

import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.base.exception.InputValidationException;
import com.dhbinh.restaurantservice.base.exception.ResourceNotFoundException;
import com.dhbinh.restaurantservice.menuitem.entity.DishType;
import com.dhbinh.restaurantservice.menuitem.entity.MenuItem;
import com.dhbinh.restaurantservice.menuitem.repository.MenuItemRepository;
import com.dhbinh.restaurantservice.menuitem.service.dto.MenuItemDTO;
import com.dhbinh.restaurantservice.menuitem.service.mapper.MenuItemMapper;
import com.dhbinh.restaurantservice.menuitem.specification.MenuItemSpecification;
import com.dhbinh.restaurantservice.restaurant.service.RestaurantService;
import com.dhbinh.restaurantservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuItemMapper menuItemMapper;

    public MenuItemDTO create(MenuItemDTO menuItemDTO) {
        verifyAndModify(menuItemDTO);

        MenuItem menuItem = MenuItem.builder()
                .name(menuItemDTO.getName())
                .description(menuItemDTO.getDescription())
                .price(menuItemDTO.getPrice())
                .dishType(DishType.valueOf(menuItemDTO.getDishType()))
                .restaurant(restaurantService.findByNameIgnoreCase(menuItemDTO.getRestaurantName().trim()))
                .build();

        return menuItemMapper.toDTO(menuItemRepository.save(menuItem));
    }

    public MenuItemDTO findById(Long id) {
        return menuItemMapper.toDTO(menuItemRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_MENU_ITEM_NOT_FOUND,
                        ErrorMessage.MENU_ITEM_NOT_FOUND)));
    }

    public List<MenuItemDTO> findAll() {
        return menuItemMapper.toDTOList(menuItemRepository.findAll());
    }

    public List<MenuItemDTO> findWithSpecifications(String name, double priceLessThan, double priceGreaterThan, String type) {
        Specification<MenuItem> spec = MenuItemSpecification.findWithSpecifications(name, priceLessThan, priceGreaterThan, type);
        return menuItemMapper.toDTOList(menuItemRepository.findAll(spec));
    }

    public MenuItemDTO update(Long id, MenuItemDTO menuItemDTO) {
        verifyAndModify(menuItemDTO);

        MenuItem menuItem = menuItemRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_MENU_ITEM_NOT_FOUND,
                        ErrorMessage.MENU_ITEM_NOT_FOUND));

        menuItemMapper.mapFromDto(menuItemDTO, menuItem);
        return menuItemMapper.toDTO(menuItemRepository.save(menuItem));
    }

    public void verifyAndModify(MenuItemDTO menuItemDTO) {
        if (menuItemDTO.getName() != null) {
            menuItemDTO.setName(Utils.capitalizeFirstWordAndAfterWhitespace(menuItemDTO.getName().trim()));
        }

        if (menuItemDTO.getDescription() != null) {
            menuItemDTO.setDescription(Utils.capitalizeFirstWord(menuItemDTO.getDescription().trim()));
        }

        if (isNameExisted(menuItemDTO.getName())) {
            throw new ResourceNotFoundException(
                    ErrorMessage.KEY_MENU_ITEM_ALREADY_EXIST,
                    ErrorMessage.MENU_ITEM_ALREADY_EXIST);
        }

        if (menuItemDTO.getPrice() < 1) {
            throw new InputValidationException(
                    ErrorMessage.KEY_PRICE_LESS_THAN_ONE,
                    ErrorMessage.PRICE_LESS_THAN_ONE);
        }
    }

    private boolean isNameExisted(String name) {
        boolean isExist = false;
        if (name != null) {
            isExist = menuItemRepository.findByNameIgnoreCase(name.trim()).isPresent();
        }
        return isExist;
    }

    public MenuItemDTO findByName(String name) {
        return menuItemMapper.toDTO(menuItemRepository.findByNameIgnoreCase(name.trim())
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_MENU_ITEM_NOT_FOUND,
                        ErrorMessage.MENU_ITEM_NOT_FOUND)));
    }
}

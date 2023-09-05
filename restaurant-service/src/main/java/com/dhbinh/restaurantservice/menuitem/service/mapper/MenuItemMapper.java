package com.dhbinh.restaurantservice.menuitem.service.mapper;

import com.dhbinh.restaurantservice.base.mapper.BaseMapper;
import com.dhbinh.restaurantservice.menuitem.entity.MenuItem;
import com.dhbinh.restaurantservice.menuitem.service.dto.MenuItemDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MenuItemMapper extends BaseMapper<MenuItem, MenuItemDTO> {

    @Mapping(target = "restaurantName", source = "restaurant.name")
    MenuItemDTO toDTO(MenuItem menuItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapFromDto(MenuItemDTO d, @MappingTarget MenuItem entity);
}

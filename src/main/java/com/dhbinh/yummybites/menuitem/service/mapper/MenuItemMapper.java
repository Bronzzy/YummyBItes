package com.dhbinh.yummybites.menuitem.service.mapper;

import com.dhbinh.yummybites.base.mapper.BaseMapper;
import com.dhbinh.yummybites.menuitem.entity.MenuItem;
import com.dhbinh.yummybites.menuitem.service.dto.MenuItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuItemMapper extends BaseMapper<MenuItem, MenuItemDTO> {

    @Mapping(target = "restaurantName", source = "restaurant.name")
    MenuItemDTO toDTO(MenuItem menuItem);
}

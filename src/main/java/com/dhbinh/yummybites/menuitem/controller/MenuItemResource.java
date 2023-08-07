package com.dhbinh.yummybites.menuitem.controller;

import com.dhbinh.yummybites.menuitem.service.MenuItemService;
import com.dhbinh.yummybites.menuitem.service.dto.MenuItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Validated
@RequestMapping(value = "/menuitems")
public class MenuItemResource {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping
    public ResponseEntity<MenuItemDTO> createMenuItem(@Valid @RequestBody MenuItemDTO menuItemDTO) {
        MenuItemDTO dto = menuItemService.create(menuItemDTO);
        return ResponseEntity.created(URI.create("/api/menuitems" + dto.getID())).body(dto);
    }
}

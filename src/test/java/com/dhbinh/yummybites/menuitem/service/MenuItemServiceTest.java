package com.dhbinh.yummybites.menuitem.service;

import com.dhbinh.yummybites.menuitem.repository.MenuItemRepository;
import com.dhbinh.yummybites.menuitem.service.mapper.MenuItemMapper;
import com.dhbinh.yummybites.utils.Utils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MenuItemServiceTest {

    @InjectMocks
    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private MenuItemMapper menuItemMapper;

    @Mock
    private Utils utils;


}
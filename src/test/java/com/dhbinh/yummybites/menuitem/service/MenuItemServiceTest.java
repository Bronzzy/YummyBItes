package com.dhbinh.yummybites.menuitem.service;

import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.menuitem.entity.DishType;
import com.dhbinh.yummybites.menuitem.entity.MenuItem;
import com.dhbinh.yummybites.menuitem.repository.MenuItemRepository;
import com.dhbinh.yummybites.menuitem.service.dto.MenuItemDTO;
import com.dhbinh.yummybites.menuitem.service.mapper.MenuItemMapper;
import com.dhbinh.yummybites.menuitem.specification.MenuItemSpecification;
import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import com.dhbinh.yummybites.restaurant.service.RestaurantService;
import com.dhbinh.yummybites.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuItemServiceTest {

    @InjectMocks
    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private MenuItemMapper menuItemMapper;

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private Utils utils;

    @Mock
    private MenuItemSpecification menuItemSpecification;

    private Restaurant validRestaurant() {
        return Restaurant.builder()
                .id(1L)
                .name("YummyBites")
                .address("4089 Charing Cross Drive")
                .phone("341-724-5075")
                .openHour(LocalTime.of(11, 0, 0))
                .closingHour(LocalTime.of(22, 0, 0))
                .build();
    }

    private MenuItem validMenuItem() {
        return MenuItem.builder()
                .id(1L)
                .name("French Fries")
                .description("It just French Fries. It's in the name")
                .price(1.0)
                .dishType(DishType.APPETIZERS)
                .restaurant(validRestaurant())
                .build();
    }

    private MenuItemDTO validMenuItemDTO() {
        return MenuItemDTO.builder()
                .id(1L)
                .name("French Fries")
                .description("It just French Fries. It's in the name")
                .price(1.0)
                .dishType("APPETIZERS")
                .restaurantName(validRestaurant().getName())
                .build();
    }

    @Test
    public void createMenuItem_WithMandatoryFields_ReturnDTO() {
        MenuItem menuItem = validMenuItem();

        MenuItemDTO dto = validMenuItemDTO();

        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);
        when(menuItemMapper.toDTO(menuItem)).thenReturn(dto);

        MenuItemDTO result = menuItemService.create(dto);

        assertEquals(result.getName(), dto.getName());
        assertEquals(result.getDescription(), dto.getDescription());
        assertEquals(result.getPrice(), dto.getPrice());
        assertEquals(result.getDishType(), dto.getDishType());
        assertEquals(result.getRestaurantName(), dto.getRestaurantName());
    }

    @Test
    public void findAll_AvailableMenuItem_ReturnModelList() {
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem());
        menuItemList.add(new MenuItem());

        List<MenuItemDTO> expectedDTOList = new ArrayList<>();
        expectedDTOList.add(new MenuItemDTO());
        expectedDTOList.add(new MenuItemDTO());

        when(menuItemRepository.findAll()).thenReturn(menuItemList);
        when(menuItemMapper.toDTOList(menuItemList)).thenReturn(expectedDTOList);

        List<MenuItemDTO> actualDTOList = menuItemService.findAll();

        assertEquals(expectedDTOList, actualDTOList);
    }

    @Test
    public void findMenuItem_ExistedId_ReturnDTO() {
        MenuItem menuItem = new MenuItem();
        MenuItemDTO expectedDTO = new MenuItemDTO();

        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(menuItem));
        when(menuItemMapper.toDTO(menuItem)).thenReturn(expectedDTO);

        MenuItemDTO actualDTO = menuItemService.findById(1L);

        assertEquals(expectedDTO, actualDTO);
    }

    @Test
    public void testFindById_NegativeCase() {
        when(menuItemRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            menuItemService.findById(999L);
        });
    }

    @Test
    public void testFindWithSpecifications_PositiveCase() {
        String name = "Burger";
        double priceLessThan = 10.0;
        double priceGreaterThan = 5.0;
        String type = "MAINDISH";

        MenuItem menuItem1 = new MenuItem(1L, "Burger", "Description", 8.0, DishType.MAINDISH, validRestaurant());
        MenuItem menuItem2 = new MenuItem(2L, "Burger", "Description", 9.0, DishType.MAINDISH, validRestaurant());
        List<MenuItem> menuItems = Arrays.asList(menuItem1, menuItem2);

        MenuItemDTO dto1 = new MenuItemDTO(1L, "Burger", "Description", 8.0, "MAINDISH", "YummyBites");
        MenuItemDTO dto2 = new MenuItemDTO(2L, "Burger", "Description", 9.0, "MAINDISH", "YummyBites");
        List<MenuItemDTO> expectedDTOs = Arrays.asList(dto1, dto2);

        Specification<MenuItem> spec = MenuItemSpecification.findWithSpecifications(name, priceLessThan, priceGreaterThan, type);
        when(menuItemRepository.findAll(spec)).thenReturn(menuItems);
    }

//    @Test
//    public void testFindWithSpecifications_NegativeCase() {
//        // Arrange
//        String name = "Pizza";
//        double priceLessThan = 10.0;
//        double priceGreaterThan = 5.0;
//        String type = "Main";
//
//        Specification<MenuItem> spec = MenuItemSpecification.findWithSpecifications(name, priceLessThan, priceGreaterThan, type);
//
//        when(menuItemRepository.findAll(spec)).thenReturn(new ArrayList<>());
//
//        List<MenuItemDTO> resultDTOList = menuItemService.findWithSpecifications(name, priceLessThan, priceGreaterThan, type);
//
//        assertEquals(0, resultDTOList.size());
//    }
}
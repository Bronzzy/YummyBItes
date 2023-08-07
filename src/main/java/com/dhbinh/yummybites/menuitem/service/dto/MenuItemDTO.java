package com.dhbinh.yummybites.menuitem.service.dto;

import com.dhbinh.yummybites.base.enumvalidate.ValueOfEnum;
import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.menuitem.entity.DishType;
import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemDTO {

    private Long ID;

    @NotBlank(message = ErrorMessage.NAME_NULL_OR_BLANK)
    private String name;

    @NotBlank(message = ErrorMessage.DESCRIPTION_NULL_OR_BLANK)
    private String description;

    @NotNull(message = ErrorMessage.PRICE_NULL_OR_BLANK)
    @Min(value = 0,message = ErrorMessage.PRICE_LESS_THAN_ZERO)
    private Double price;

    @NotNull(message = ErrorMessage.DISH_TYPE_NULL_OR_BLANK)
    @ValueOfEnum(enumClass = DishType.class, message = ErrorMessage.DISH_TYPE_INVALID_VALUE)
    @Enumerated(EnumType.STRING)
    private String dishType;

    @NotNull(message = ErrorMessage.RESTAURANT_NAME_NULL_OR_BLANK)
    private String restaurantName;
}

package com.dhbinh.yummybites.menuitem.entity;

import com.dhbinh.yummybites.base.enumvalidate.ValueOfEnum;
import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = ErrorMessage.NAME_NULL_OR_BLANK)
    private String name;

    @Column(nullable = false)
    @NotBlank(message = ErrorMessage.DESCRIPTION_NULL_OR_BLANK)
    private String description;

    @Column(nullable = false)
    @NotNull(message = ErrorMessage.PRICE_NULL_OR_BLANK)
    @Min(value = 1,message = ErrorMessage.PRICE_LESS_THAN_ONE)
    private Double price;

    @Column(nullable = false)
    @NotNull(message = ErrorMessage.DISH_TYPE_NULL_OR_BLANK)
    @ValueOfEnum(enumClass = DishType.class)
    @Enumerated(EnumType.STRING)
    private DishType dishType;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull(message = ErrorMessage.RESTAURANT_NAME_NULL_OR_BLANK)
    private Restaurant restaurant;
}

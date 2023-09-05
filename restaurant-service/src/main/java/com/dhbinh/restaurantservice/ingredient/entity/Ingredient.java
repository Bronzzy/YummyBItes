package com.dhbinh.restaurantservice.ingredient.entity;

import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.restaurant.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = ErrorMessage.INGREDIENT_NULL_OR_BLANK)
    private String name;

    @Column(nullable = false)
    @NotNull(message = ErrorMessage.INGREDIENT_QUANTITY_NULL_OR_BLANK)
    @Min(value = 1, message = ErrorMessage.INGREDIENT_QUANTITY_LESS_THAN_ONE)
    private Double quantity;

    @ManyToOne
    @JoinColumn(name = "restaurant", nullable = false)
    @NotNull(message = ErrorMessage.RESTAURANT_NAME_NULL_OR_BLANK)
    private Restaurant restaurant;
}

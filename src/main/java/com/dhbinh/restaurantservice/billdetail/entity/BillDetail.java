package com.dhbinh.restaurantservice.billdetail.entity;

import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.bill.entity.Bill;
import com.dhbinh.restaurantservice.ingredient.entity.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    @NotNull(message = ErrorMessage.INGREDIENT_NULL_OR_BLANK)
    private Ingredient ingredient;

    @Column(nullable = false)
    @Min(value = 1, message = ErrorMessage.QUANTITY_LESS_THAN_ONE)
    private Double quantity;

    @Column(nullable = false)
    @Min(value = 1, message = ErrorMessage.PRICE_LESS_THAN_ONE)
    private Double pricePerUnit;

    @Column(nullable = false)
    @Min(value = 1, message = ErrorMessage.PRICE_LESS_THAN_ONE)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    @NotNull(message = ErrorMessage.BILL_NULL_OR_BLANK)
    private Bill bill;

}

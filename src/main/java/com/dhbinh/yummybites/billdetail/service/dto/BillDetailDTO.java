package com.dhbinh.yummybites.billdetail.service.dto;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDetailDTO {

    private Long id;

    @NotBlank(message = ErrorMessage.INGREDIENT_NULL_OR_BLANK)
    private String ingredientName;

    @NotNull(message = ErrorMessage.QUANTITY_NULL_OR_BLANK)
    @Min(value = 1, message = ErrorMessage.QUANTITY_LESS_THAN_ONE)
    private Double quantity;

    @NotNull(message = ErrorMessage.PRICE_PER_UNIT_NULL_OR_BLANK)
    @Min(value = 1, message = ErrorMessage.PRICE_LESS_THAN_ONE)
    private Double pricePerUnit;

    @Min(value = 1, message = ErrorMessage.PRICE_LESS_THAN_ONE)
    private Double price;

    private Long billId;
}

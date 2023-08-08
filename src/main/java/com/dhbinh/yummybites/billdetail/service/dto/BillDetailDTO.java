package com.dhbinh.yummybites.billdetail.service.dto;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDetailDTO {

    private Long id;

    @NotBlank(message = ErrorMessage.INGREDIENT_NULL_OR_BLANK)
    private String ingredient;

    @NotBlank(message = ErrorMessage.QUANTITY_NULL_OR_BLANK)
    @Min(value = 0, message = ErrorMessage.QUANTITY_LESS_THAN_ZERO)
    private Double quantity;

    @NotBlank(message = ErrorMessage.PRICE_PER_UNIT_NULL_OR_BLANK)
    @Min(value = 0, message = ErrorMessage.PRICE_LESS_THAN_ZERO)
    private Double pricePerUnit;

    @NotBlank(message = ErrorMessage.PRICE_NULL_OR_BLANK)
    @Min(value = 0, message = ErrorMessage.PRICE_LESS_THAN_ZERO)
    private Double price;

    private Long billId;
}

package com.dhbinh.yummybites.orderdetail.service.dto;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailDTO {

    private Long id;

    @NotBlank(message = ErrorMessage.MENU_ITEM_NULL_OR_BLANK)
    private String menuItemName;

    @NotNull(message = ErrorMessage.QUANTITY_NULL_OR_BLANK)
    @Min(value = 1, message = ErrorMessage.QUANTITY_LESS_THAN_ONE)
    private Integer quantity;

    @Min(value = 1, message = ErrorMessage.PRICE_LESS_THAN_ONE)
    private Double price;

    private Long orderId;
}

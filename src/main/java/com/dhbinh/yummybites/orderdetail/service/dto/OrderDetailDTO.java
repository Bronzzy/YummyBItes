package com.dhbinh.yummybites.orderdetail.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDTO {

    private Long id;

    @NotBlank
    private String menuItemName;

    @NotNull
    private Integer quantity;

    private Double price;

    private Long orderId;
}

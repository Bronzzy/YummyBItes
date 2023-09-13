package com.dhbinh.restaurantservice.onlineorderdetail.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnlineOrderDetailDTO {

    private Long id;

    private String menuItemName;

    private Integer quantity;

    private Double price;

    private Long onlineOrderId;
}

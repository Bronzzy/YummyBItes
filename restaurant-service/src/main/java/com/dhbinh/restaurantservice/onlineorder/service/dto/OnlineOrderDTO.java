package com.dhbinh.restaurantservice.onlineorder.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OnlineOrderDTO {

    private Long id;

    private String destination;

    private Double deliveryFee;

    private String customerPhone;

    private Double totalPrice;

    private Boolean isPaid;

    private LocalDateTime createdDate;
}

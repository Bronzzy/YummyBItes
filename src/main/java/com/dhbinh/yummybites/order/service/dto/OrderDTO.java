package com.dhbinh.yummybites.order.service.dto;

import com.dhbinh.yummybites.orderdetail.service.dto.OrderDetailDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    private Long id;

    private List<OrderDetailDTO> orderDetails;

    private Integer diningTableNumber;

    private String employeeName;

    private Double totalPrice;

    private Boolean isPaid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdDate;
}
